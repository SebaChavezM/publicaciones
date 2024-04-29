package com.example.publicaciones.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ComentarioNotFoundException extends RuntimeException {
    public ComentarioNotFoundException(Long id) {
        super("No se encontró el comentario con ID: " + id);
    }
}
