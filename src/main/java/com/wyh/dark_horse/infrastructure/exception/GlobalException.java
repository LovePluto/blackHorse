package com.wyh.dark_horse.infrastructure.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE;

@ControllerAdvice
@Slf4j
public class GlobalException {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(code = INTERNAL_SERVER_ERROR)
    public ResponseBody handlerException(Exception e) {
        log.error("unknown error!", e);
        return ResponseBody.ERROR;
    }

    @ExceptionHandler(TicketException.class)
    @ResponseStatus(code = CONFLICT)
    public ResponseBody handlerTicketException(TicketException e) {
        log.error("ticket failed!", e);
        return new ResponseBody(4001, e.getMessage());
    }

    @ExceptionHandler(ThirdException.class)
    @ResponseStatus(code = SERVICE_UNAVAILABLE)
    public ResponseBody handlerTicketException(ThirdException e) {
        log.error("third system unavailable !", e);
        return new ResponseBody(5002, e.getMessage());
    }
}
