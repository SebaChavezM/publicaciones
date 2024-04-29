package com.example.publicaciones.service;

import com.example.publicaciones.model.Calificacion;

public interface CalificacionService {
    Calificacion crearCalificacion(Long publicacionId, Calificacion calificacion);
    Calificacion actualizarCalificacion(Long id, Calificacion calificacion);
    void eliminarCalificacion(Long id);
    Calificacion obtenerCalificacionPorId(Long id);
}
