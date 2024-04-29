package com.example.publicaciones.service;

import java.util.List;

import com.example.publicaciones.model.Comentario;

public interface ComentarioService {
    List<Comentario> obtenerComentariosPorPublicacion(Long publicacionId);
    Comentario crearComentario(Long publicacionId, Comentario comentario);
    Comentario actualizarComentario(Long id, Comentario comentario);
    void eliminarComentario(Long id);
    Comentario obtenerComentarioPorId(Long id);
}