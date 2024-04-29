package com.example.publicaciones.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CalificacionNotFoundException extends RuntimeException {
    public CalificacionNotFoundException(Long id) {
        super("No se encontró la calificación con ID: " + id);
    }
}
