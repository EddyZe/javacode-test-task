package ru.edd.javacodetesttask.exception.advice;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.edd.javacodetesttask.exception.TransactionException;
import ru.edd.javacodetesttask.exception.WalletNotFoundException;
import ru.edd.javacodetesttask.models.BadResponse;

import java.time.LocalDateTime;

@RestControllerAdvice
public class AdviceController {

    @ExceptionHandler(WalletNotFoundException.class)
    public ResponseEntity<BadResponse> handleWalletNotFoundException(WalletNotFoundException e) {
        BadResponse badResponse = new BadResponse(e.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(badResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TransactionException.class)
    public ResponseEntity<BadResponse> handleTransactionException(TransactionException e) {
        BadResponse badResponse = new BadResponse(e.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(badResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<BadResponse> handleUUIDException(HttpMessageNotReadableException e) {
        BadResponse badResponse = new BadResponse(e.getLocalizedMessage(), LocalDateTime.now());
        return new ResponseEntity<>(badResponse, HttpStatus.BAD_REQUEST);
    }
}
