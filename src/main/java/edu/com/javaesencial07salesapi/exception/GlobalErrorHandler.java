package edu.com.javaesencial07salesapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;


import java.net.URI;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(ModelNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> handleModelNotFoundException(ModelNotFoundException ex , WebRequest req){
        CustomErrorResponse error = new CustomErrorResponse
                (LocalDateTime.now(), ex.getMessage(),req.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }


//    // DESDE SPRING BOOT 3 + ProblemDetail
//    @ExceptionHandler(ModelNotFoundException.class)
//    public ProblemDetail handleModelNotFoundException(ModelNotFoundException ex , WebRequest req){
//        ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
//        pd.setTitle("Model not found ");
//        pd.setType(URI.create(req.getContextPath()));
//        pd.setProperty("path", req.getDescription(false));
//        pd.setProperty("timestamp", LocalDateTime.now());
//        pd.setProperty("message", ex.getMessage());
//        return pd;
//    }
}
