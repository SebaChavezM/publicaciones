package com.example.publicaciones.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.publicaciones.controller.CalificacionNotFoundException;
import com.example.publicaciones.model.Calificacion;
import com.example.publicaciones.model.Publicacion;
import com.example.publicaciones.repository.CalificacionRepository;

@Service
public class CalificacionServiceImpl implements CalificacionService {
    @Autowired
    private CalificacionRepository calificacionRepository;
    @Autowired
    private PublicacionService publicacionService;

    @Override
    public Calificacion crearCalificacion(Long publicacionId, Calificacion calificacion) {
        Publicacion publicacion = publicacionService.obtenerPublicacionPorId(publicacionId);
        calificacion.setPublicacion(publicacion);
        return calificacionRepository.save(calificacion);
    }

    @Override
    public Calificacion actualizarCalificacion(Long id, Calificacion calificacion) {
        Calificacion calificacionExistente = obtenerCalificacionPorId(id);
        calificacionExistente.setValor(calificacion.getValor());
        return calificacionRepository.save(calificacionExistente);
    }

    @Override
    public void eliminarCalificacion(Long id) {
        calificacionRepository.deleteById(id);
    }

    @Override
    public Calificacion obtenerCalificacionPorId(Long id) {
        return calificacionRepository.findById(id)
                .orElseThrow(() -> new CalificacionNotFoundException(id));
    }
}