package com.example.publicaciones.service;

import com.example.publicaciones.controller.PublicacionNotFoundException;
import com.example.publicaciones.model.Calificacion;
import com.example.publicaciones.model.Publicacion;
import com.example.publicaciones.repository.PublicacionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PublicacionServiceImplTest {

    @Mock
    private PublicacionRepository publicacionRepository;

    @InjectMocks
    private PublicacionServiceImpl publicacionService;

    @Test
    void testObtenerTodasLasPublicaciones() {
        // Arrange
        List<Publicacion> publicaciones = Arrays.asList(
                new Publicacion("Título 1", "Contenido 1", LocalDate.now()),
                new Publicacion("Título 2", "Contenido 2", LocalDate.now())
        );
        when(publicacionRepository.findAll()).thenReturn(publicaciones);

        // Act
        List<Publicacion> resultado = publicacionService.obtenerTodasLasPublicaciones();

        // Assert
        assertEquals(publicaciones.size(), resultado.size());
    }

    @Test
    void testObtenerPublicacionPorId() {
        // Arrange
        Long id = 1L;
        Publicacion publicacion = new Publicacion("Título", "Contenido", LocalDate.now());
        when(publicacionRepository.findById(id)).thenReturn(Optional.of(publicacion));

        // Act
        Publicacion resultado = publicacionService.obtenerPublicacionPorId(id);

        // Assert
        assertEquals(publicacion, resultado);
    }

    @Test
    void testObtenerPublicacionPorIdNoEncontrada() {
        // Arrange
        Long id = 1L;
        when(publicacionRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(PublicacionNotFoundException.class, () -> publicacionService.obtenerPublicacionPorId(id));
    }

    @Test
    void testCalcularPromedioDeCalificaciones() {
        // Arrange
        Long publicacionId = 1L;
        Publicacion publicacion = new Publicacion("Título", "Contenido", LocalDate.now());
        Calificacion calificacion1 = new Calificacion(4);
        Calificacion calificacion2 = new Calificacion(3);
        publicacion.setCalificaciones(Arrays.asList(calificacion1, calificacion2));
        when(publicacionRepository.findById(publicacionId)).thenReturn(Optional.of(publicacion));

        // Act
        double promedio = publicacionService.calcularPromedioDeCalificaciones(publicacionId);

        // Assert
        assertEquals(3.5, promedio);
    }

    // Otros métodos de prueba para los demás métodos del servicio
}