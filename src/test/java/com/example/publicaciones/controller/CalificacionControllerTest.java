package com.example.publicaciones.controller;

import com.example.publicaciones.model.Calificacion;
import com.example.publicaciones.service.CalificacionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CalificacionControllerTest {

    @Mock
    private CalificacionService calificacionService;

    @InjectMocks
    private CalificacionController calificacionController;

    @Test
    void testCrearCalificacion() {
        // Arrange
        Long publicacionId = 1L;
        Calificacion calificacion = new Calificacion();
        calificacion.setValor(4);

        Calificacion calificacionCreada = new Calificacion();
        calificacionCreada.setId(1L);
        calificacionCreada.setValor(4);

        when(calificacionService.crearCalificacion(eq(publicacionId), any(Calificacion.class))).thenReturn(calificacionCreada);

        // Act
        ResponseEntity<EntityModel<Calificacion>> respuesta = calificacionController.crearCalificacion(publicacionId, calificacion);

        // Assert
        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
    }
}