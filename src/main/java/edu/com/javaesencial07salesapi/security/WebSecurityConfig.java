package edu.com.javaesencial07salesapi.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.server.SecurityWebFilterChain;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;


// clase 07
@Profile(value = {"dev","qa","prod"})
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtAuthenticationsEntryPoint jwtAuthenticationsEntryPoint;
    private final UserDetailsService userDetailsService;
    private final JwtRequestFilter jwtRequestFilter;

    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Desde spring boot 3.1+
        http
                .csrf(e -> e.disable())
                        .authorizeHttpRequests(req -> req
                                .requestMatchers(antMatcher("/login")).permitAll()
                                .requestMatchers(antMatcher("/rest/**")).permitAll()
                                //.requestMatchers(antMatcher("/api/category/**")).permitAll()
                                .requestMatchers(antMatcher("/swagger-ui.html")).permitAll()
                                .requestMatchers(antMatcher("/swagger-ui/**")).permitAll()
                                .requestMatchers(antMatcher("/v3/api-docs/**")).permitAll()
                                .anyRequest().authenticated()
                        )
                        .exceptionHandling(e -> e
                                .authenticationEntryPoint(jwtAuthenticationsEntryPoint)
                        )
                        .formLogin(e ->e.disable());
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
