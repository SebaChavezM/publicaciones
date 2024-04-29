package com.example.publicaciones.controller;

import com.example.publicaciones.model.Publicacion;
import com.example.publicaciones.service.PublicacionService;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PublicacionControllerTest {

    @Mock
    private PublicacionService publicacionService;

    @InjectMocks
    private PublicacionController publicacionController;

    @Test
    void testObtenerTodasLasPublicaciones() {
        // Arrange
        Publicacion publicacion1 = new Publicacion();
        publicacion1.setId(1L);
        publicacion1.setTitulo("Publicación 1");
        publicacion1.setContenido("Contenido de la publicación 1");

        Publicacion publicacion2 = new Publicacion();
        publicacion2.setId(2L);
        publicacion2.setTitulo("Publicación 2");
        publicacion2.setContenido("Contenido de la publicación 2");

        List<Publicacion> publicaciones = Arrays.asList(publicacion1, publicacion2);

        when(publicacionService.obtenerTodasLasPublicaciones()).thenReturn(publicaciones);

        // Act
        ResponseEntity<CollectionModel<EntityModel<Publicacion>>> respuesta = publicacionController.obtenerTodasLasPublicaciones();

        // Assert
        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
    }
}