package com.entrevista.builders.controller;

import com.entrevista.builders.exception.unchecked.NegocioException;
import com.entrevista.builders.exception.unchecked.ObjetoInvalidoException;
import com.entrevista.builders.exception.unchecked.ObjetoNaoEncontrado;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value
            = {ObjetoInvalidoException.class})
    protected ResponseEntity<Object> handlerBadRequest(
            ObjetoInvalidoException ex, WebRequest request) {

        return handleExceptionInternal(ex, ex.getMessage(),
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);

    }

    @ExceptionHandler(value
            = {ObjetoNaoEncontrado.class})
    protected ResponseEntity<Object> handlerNotFound(
            ObjetoNaoEncontrado ex, WebRequest request) {

        return handleExceptionInternal(ex, ex.getMessage(),
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);

    }

    @ExceptionHandler(value
            = {NegocioException.class})
    protected ResponseEntity<Object> handlerUnprocessableEntity(
            NegocioException ex, WebRequest request) {

        return handleExceptionInternal(ex, ex.getMessage(),
                new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY, request);

    }

}
