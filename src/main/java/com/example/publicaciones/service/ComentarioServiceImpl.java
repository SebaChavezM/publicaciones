package com.example.publicaciones.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.publicaciones.controller.ComentarioNotFoundException;
import com.example.publicaciones.model.Comentario;
import com.example.publicaciones.model.Publicacion;
import com.example.publicaciones.repository.ComentarioRepository;

import java.util.List;

@Service
public class ComentarioServiceImpl implements ComentarioService {
    @Autowired
    private ComentarioRepository comentarioRepository;
    @Autowired
    private PublicacionService publicacionService;

    @Override
    public List<Comentario> obtenerComentariosPorPublicacion(Long publicacionId) {
        return comentarioRepository.findByPublicacionId(publicacionId);
    }

    @Override
    public Comentario crearComentario(Long publicacionId, Comentario comentario) {
        Publicacion publicacion = publicacionService.obtenerPublicacionPorId(publicacionId);
        comentario.setPublicacion(publicacion);
        return comentarioRepository.save(comentario);
    }

    @Override
    public Comentario actualizarComentario(Long id, Comentario comentario) {
        Comentario comentarioExistente = obtenerComentarioPorId(id);
        comentarioExistente.setTexto(comentario.getTexto());
        comentarioExistente.setFecha(comentario.getFecha());
        return comentarioRepository.save(comentarioExistente);
    }

    @Override
    public void eliminarComentario(Long id) {
        comentarioRepository.deleteById(id);
    }

    @Override
    public Comentario obtenerComentarioPorId(Long id) {
        return comentarioRepository.findById(id)
                .orElseThrow(() -> new ComentarioNotFoundException(id));
    }
}