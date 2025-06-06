package edu.com.javaesencial07salesapi.security;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.com.javaesencial07salesapi.exception.CustomErrorResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

// Clase S6
@Component
public class JwtAuthenticationsEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException)
            throws IOException, ServletException {

        String message = (String)  request.getAttribute("error");

        if (message == null) {
            message = "Invalid token , failed autentication";
        }

        CustomErrorResponse errorResponse =  new CustomErrorResponse(LocalDateTime.now(),message, request.getRequestURI());

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        response.getWriter().write(converObjectecToJson(errorResponse));

    }

    // metodo de convertir a json
    private String converObjectecToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return "null";
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        return mapper.writeValueAsString(object);
    }
}
