package com.example.publicaciones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.publicaciones.model.Calificacion;

@Repository
public interface CalificacionRepository extends JpaRepository<Calificacion, Long> {

}