package tech.gustavomedina.cryptography.handler;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tech.gustavomedina.cryptography.dto.ExceptionResponse;
import tech.gustavomedina.cryptography.exception.TransactionNotFoundException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TransactionNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleTransactionNotFoundException(TransactionNotFoundException ex, HttpServletRequest request){

        var status = HttpStatus.NOT_FOUND;
        var response = ExceptionResponse.builder()
                .date(LocalDateTime.now())
                .method(request.getMethod())
                .status(status.value())
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(status).body(response);
    }

}
