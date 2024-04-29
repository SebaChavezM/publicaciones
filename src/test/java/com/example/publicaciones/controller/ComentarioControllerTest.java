package com.example.publicaciones.controller;

import com.example.publicaciones.model.Comentario;
import com.example.publicaciones.service.ComentarioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ComentarioControllerTest {

    @Mock
    private ComentarioService comentarioService;

    @InjectMocks
    private ComentarioController comentarioController;

    @Test
    void testObtenerComentariosPorPublicacion() {
        // Arrange
        Long publicacionId = 1L;

        Comentario comentario1 = new Comentario();
        comentario1.setId(1L);
        comentario1.setTexto("Comentario 1");

        Comentario comentario2 = new Comentario();
        comentario2.setId(2L);
        comentario2.setTexto("Comentario 2");

        List<Comentario> comentarios = Arrays.asList(comentario1, comentario2);

        when(comentarioService.obtenerComentariosPorPublicacion(eq(publicacionId))).thenReturn(comentarios);

        // Act
        ResponseEntity<CollectionModel<EntityModel<Comentario>>> respuesta = comentarioController.obtenerComentariosPorPublicacion(publicacionId);

        // Assert
        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
    }
}