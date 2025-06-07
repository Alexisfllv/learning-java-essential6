package edu.com.javaesencial07salesapi.security;


import edu.com.javaesencial07salesapi.entity.User;
import edu.com.javaesencial07salesapi.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// clase s2
@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findOneByUserName(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        List<GrantedAuthority> roles = new ArrayList<>();
        String rol =  user.getRole().getRoleName();
        roles.add(new SimpleGrantedAuthority(rol));
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),roles);

    }
}
