package edu.com.javaesencial07salesapi.security;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

// clase s5
@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {


    private final JwtUserDetailsService jwtUserDetailsService;
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        final String header =  request.getHeader("Authorization");

        String username = null;
        String jwtToken = null;


        if (header != null && header.startsWith("Bearer ")) {
            jwtToken = header.substring(7);

            try {
                username = jwtTokenUtil.getUsernameFromToken(jwtToken);
            } catch (Exception e) {
                request.setAttribute("error", e.getMessage());
            }
        }

        if (username != null && jwtToken != null) {
            UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);

            if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                // sp3.0
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }

        filterChain.doFilter(request, response);

        // Bearer <Token>

    }
}
