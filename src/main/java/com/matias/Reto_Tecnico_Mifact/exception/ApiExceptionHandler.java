package com.matias.Reto_Tecnico_Mifact.exception;

import com.matias.Reto_Tecnico_Mifact.model.dto.ErrorMessageDto;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorMessageDto badRequest(HttpServletRequest request, MethodArgumentNotValidException exception) {

        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach((error) -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        return new ErrorMessageDto("Validation failed", exception.getClass().getSimpleName(), request.getRequestURI(), errors);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ErrorMessageDto conflict(HttpServletRequest request, DataIntegrityViolationException exception) {
        return new ErrorMessageDto("Product code already exists or database constraint violation",
                exception.getClass().getSimpleName(),
                request.getRequestURI());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ErrorMessageDto notFound(HttpServletRequest request, Exception exception) {
        return new ErrorMessageDto(exception.getMessage(), exception.getClass().getSimpleName(), request.getRequestURI());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorMessageDto exception(HttpServletRequest request, Exception exception) {
        return new ErrorMessageDto(exception.getMessage(), exception.getClass().getSimpleName(), request.getRequestURI());
    }
}
