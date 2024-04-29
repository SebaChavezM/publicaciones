package com.example.publicaciones.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.publicaciones.model.Calificacion;
import com.example.publicaciones.service.CalificacionService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/calificaciones")
public class CalificacionController {

    @Autowired
    private CalificacionService calificacionService;

    @PostMapping("/publicacion/{publicacionId}")
    public ResponseEntity<EntityModel<Calificacion>> crearCalificacion(@PathVariable Long publicacionId, @RequestBody Calificacion calificacion) {
        Calificacion calificacionCreada = calificacionService.crearCalificacion(publicacionId, calificacion);
        EntityModel<Calificacion> calificacionResource = EntityModel.of(calificacionCreada,
            linkTo(methodOn(this.getClass()).obtenerCalificacionPorId(calificacionCreada.getId())).withSelfRel(),
            linkTo(methodOn(PublicacionController.class).obtenerPublicacionPorId(publicacionId)).withRel("publicacion")
        );
        return ResponseEntity.ok(calificacionResource);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Calificacion>> actualizarCalificacion(@PathVariable Long id, @RequestBody Calificacion calificacion) {
        Calificacion calificacionActualizada = calificacionService.actualizarCalificacion(id, calificacion);
        EntityModel<Calificacion> calificacionResource = EntityModel.of(calificacionActualizada,
            linkTo(methodOn(this.getClass()).obtenerCalificacionPorId(id)).withSelfRel()
        );
        return ResponseEntity.ok(calificacionResource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCalificacion(@PathVariable Long id) {
        calificacionService.eliminarCalificacion(id);
        return ResponseEntity.noContent().build();
    }

    // Método auxiliar para obtener un enlace a una calificación específica
    public EntityModel<Calificacion> obtenerCalificacionPorId(Long id) {
        Calificacion calificacion = calificacionService.obtenerCalificacionPorId(id);
        return EntityModel.of(calificacion,
            linkTo(methodOn(this.getClass()).obtenerCalificacionPorId(id)).withSelfRel()
        );
    }
}
