package edu.com.javaesencial07salesapi.exception;

import java.time.LocalDateTime;

public record CustomErrorResponse(
        LocalDateTime datetime,
        String message,
        String path
        ) {
}
