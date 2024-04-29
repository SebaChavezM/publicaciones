package com.example.publicaciones.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.publicaciones.controller.PublicacionNotFoundException;
import com.example.publicaciones.model.Calificacion;
import com.example.publicaciones.model.Publicacion;
import com.example.publicaciones.repository.PublicacionRepository;

import java.util.List;
import java.util.OptionalDouble;

@Service
public class PublicacionServiceImpl implements PublicacionService {
    @Autowired
    private PublicacionRepository publicacionRepository;

    @Override
    public List<Publicacion> obtenerTodasLasPublicaciones() {
        return publicacionRepository.findAll();
    }

    @Override
    public Publicacion obtenerPublicacionPorId(Long id) {
        return publicacionRepository.findById(id)
                .orElseThrow(() -> new PublicacionNotFoundException(id));
    }

    @Override
    public Publicacion crearPublicacion(Publicacion publicacion) {
        return publicacionRepository.save(publicacion);
    }

    @Override
    public Publicacion actualizarPublicacion(Long id, Publicacion publicacion) {
        Publicacion publicacionExistente = obtenerPublicacionPorId(id);
        publicacionExistente.setTitulo(publicacion.getTitulo());
        publicacionExistente.setContenido(publicacion.getContenido());
        publicacionExistente.setFecha(publicacion.getFecha());
        return publicacionRepository.save(publicacionExistente);
    }

    @Override
    public void eliminarPublicacion(Long id) {
        publicacionRepository.deleteById(id);
    }

    @Override
    public double calcularPromedioDeCalificaciones(Long publicacionId) {
        Publicacion publicacion = obtenerPublicacionPorId(publicacionId);
        List<Calificacion> calificaciones = publicacion.getCalificaciones();

        OptionalDouble promedio = calificaciones.stream()
                .mapToInt(Calificacion::getValor)
                .average();

        return promedio.orElse(0.0);
    }
}