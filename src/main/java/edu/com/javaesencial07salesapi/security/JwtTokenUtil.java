package edu.com.javaesencial07salesapi.security;


import edu.com.javaesencial07salesapi.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

// CLASE S1

@Component
public class JwtTokenUtil implements Serializable {

    private final long JWT_TOKEN_VALIDITY_SECONDS = 5 * 60 * 60 *1000;

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(UserDetails userDetails) {


        // agregar data al payload
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", userDetails.getAuthorities()
                .stream()
                .map(e -> e.getAuthority())
                .collect(Collectors.joining(",")));

        claims.put("test", "value-test");
        claims.put("test2", 50);

        return doGenerateToken(claims, userDetails.getUsername());

    }


    private String doGenerateToken(Map<String, Object> claims, String username) {

        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());

        return Jwts.builder()
                .claims(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+ JWT_TOKEN_VALIDITY_SECONDS))
                .signWith(key)
                .compact();
    }

    // validaciones
    public Claims getallClaimsFromToken(String token){
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());

        return Jwts.parser().verifyWith(key).build().parseClaimsJws(token).getPayload();

    }

    public <T> T getClaimsFromToken(String token, Function<Claims,T> claimsResolver){

        final  Claims claims = getallClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    // saber el nombre de usuario del token
    public String getUsernameFromToken(String token){
        return getClaimsFromToken(token, e -> e.getSubject());
    }

    // saber el timepo de expiracion del token
    public Date getExpirationDateFromToken(String token){
        return getClaimsFromToken(token, e -> e.getExpiration());
    }

    //
    private boolean isTokenExpired(String token){
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

}
