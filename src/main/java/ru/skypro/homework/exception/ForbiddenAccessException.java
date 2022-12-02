package ru.skypro.homework.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenAccessException extends RuntimeException{
    public ForbiddenAccessException(String s) {
        super(s);
    }
}
