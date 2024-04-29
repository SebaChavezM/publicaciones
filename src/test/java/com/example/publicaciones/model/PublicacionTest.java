package com.example.publicaciones.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PublicacionTest {

    @Test
    void testConstructor() {
        // Arrange
        String titulo = "Título de Prueba";
        String contenido = "Contenido de Prueba";
        LocalDate fecha = LocalDate.now();

        // Act
        Publicacion publicacion = new Publicacion(titulo, contenido, fecha);

        // Assert
        assertEquals(titulo, publicacion.getTitulo());
        assertEquals(contenido, publicacion.getContenido());
        assertEquals(fecha, publicacion.getFecha());
    }

    @Test
    void testGettersAndSetters() {
        // Arrange
        Publicacion publicacion = new Publicacion();
        Long id = 1L;
        String titulo = "Título de Prueba";
        String contenido = "Contenido de Prueba";
        LocalDate fecha = LocalDate.now();
        List<Comentario> comentarios = new ArrayList<>();
        List<Calificacion> calificaciones = new ArrayList<>();

        // Act
        publicacion.setId(id);
        publicacion.setTitulo(titulo);
        publicacion.setContenido(contenido);
        publicacion.setFecha(fecha);
        publicacion.setComentarios(comentarios);
        publicacion.setCalificaciones(calificaciones);

        // Assert
        assertEquals(id, publicacion.getId());
        assertEquals(titulo, publicacion.getTitulo());
        assertEquals(contenido, publicacion.getContenido());
        assertEquals(fecha, publicacion.getFecha());
        assertEquals(comentarios, publicacion.getComentarios());
        assertEquals(calificaciones, publicacion.getCalificaciones());
    }
}