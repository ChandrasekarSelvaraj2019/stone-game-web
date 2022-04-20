package com.game.stonegame.exception.handler;

import com.game.stonegame.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class StoneGameExceptionHandler {

    private static final String GENERIC_ERROR_MESSAGE = "The application has encountered an error.";


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleGenericException(Exception exception) {
        log.error("A generic exception was thrown", exception);
        return new ErrorResponse(GENERIC_ERROR_MESSAGE, "SG1001");
    }

}
