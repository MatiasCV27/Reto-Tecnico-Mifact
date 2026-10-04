package com.matias.Reto_Tecnico_Mifact.model.dto;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ErrorMessageDto {

    private String message;
    private String exception;
    private String path;
    private Map<String, String> errors;

    public ErrorMessageDto(String path, String exception, String message) {
        this.message = message;
        this.exception = exception;
        this.path = path;
        this.errors = new HashMap<>();
    }

    public ErrorMessageDto(String message, String exception, String path, Map<String, String> errors) {
        this.message = message;
        this.exception = exception;
        this.path = path;
        this.errors = errors;
    }
}
