package kz.axelrodadil.bookstore_samgau.controller;

import kz.axelrodadil.bookstore_samgau.dto.ApiResponse;
import kz.axelrodadil.bookstore_samgau.exception.InternalServerErrorException;
import kz.axelrodadil.bookstore_samgau.exception.UnauthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ErrorHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleError(Exception e) {
        log.error("Uncaught error", e);
        int status;
        String message;

        if (e instanceof UnauthorizedException) {
            status = 401;
            message = e.getMessage();
        } else if (e instanceof InternalServerErrorException) {
            status = 500;
            message = e.getMessage();
        } else {
            status = 400;
            message = e.getMessage();
        }
        return ResponseEntity.status(status).body(ApiResponse.fail(message));
    }
}
