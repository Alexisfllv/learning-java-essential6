package edu.com.javaesencial07salesapi.service.implementation;


import edu.com.javaesencial07salesapi.entity.User;
import edu.com.javaesencial07salesapi.repo.UserRepo;
import edu.com.javaesencial07salesapi.repo.GenericRepo;
import edu.com.javaesencial07salesapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl extends CRUDIMPL<User,Long> implements UserService {

    private final UserRepo userRepo;


    @Override
    protected GenericRepo<User, Long> getRepo() {
        return userRepo;
    }

}
