package edu.com.javaesencial07salesapi.security;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// CLASE S3
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtRequest {

    private String username;
    private String password;

}
