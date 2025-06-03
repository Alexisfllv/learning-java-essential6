package edu.com.javaesencial07salesapi.exception;

import edu.com.javaesencial07salesapi.apiresponse.GenResponse;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import java.net.URI;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalErrorHandler extends ResponseEntityExceptionHandler {

    // Exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomErrorResponse> handleDefaultException(Exception ex , WebRequest req){
        CustomErrorResponse error = new CustomErrorResponse
                (LocalDateTime.now(), ex.getMessage(),req.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    // dato no encontrado VERSION 1
//    @ExceptionHandler(ModelNotFoundException.class)
//    public ResponseEntity<CustomErrorResponse> handleModelNotFoundException(ModelNotFoundException ex , WebRequest req){
//        CustomErrorResponse error = new CustomErrorResponse
//                (LocalDateTime.now(), ex.getMessage(),req.getDescription(false));
//        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
//    }

    // genre VERSION 2 CON RESPONSE
    @ExceptionHandler(ModelNotFoundException.class)
    public ResponseEntity<GenResponse<CustomErrorResponse>> handleModelNotFoundException(ModelNotFoundException ex , WebRequest req){
        CustomErrorResponse error = new CustomErrorResponse
                (LocalDateTime.now(), ex.getMessage(),req.getDescription(false));
        return new ResponseEntity<>(new GenResponse<>(404, "error", Arrays.asList(error)), HttpStatus.NOT_FOUND);
    }

//    // datos mal enviados @Valid
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<CustomErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex , WebRequest req){
//        CustomErrorResponse error = new CustomErrorResponse
//                (LocalDateTime.now(), ex.getMessage(),req.getDescription(false));
//        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
//    }


    // metodo sobreescrito de ReponseEntityExceptionHandler

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest req) {

        String message = ex.getBindingResult().getFieldErrors().stream()
                .map( error -> error.getField() + " : " + error.getDefaultMessage()).collect(Collectors.joining(","));

        CustomErrorResponse error = new CustomErrorResponse
                (LocalDateTime.now(), message,req.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
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
