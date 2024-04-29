package com.example.publicaciones.service;

import java.util.List;
import com.example.publicaciones.model.Publicacion;

public interface PublicacionService {
    List<Publicacion> obtenerTodasLasPublicaciones();
    Publicacion obtenerPublicacionPorId(Long id);
    Publicacion crearPublicacion(Publicacion publicacion);
    Publicacion actualizarPublicacion(Long id, Publicacion publicacion);
    void eliminarPublicacion(Long id);
    double calcularPromedioDeCalificaciones(Long publicacionId);
}