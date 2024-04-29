package com.example.publicaciones.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.publicaciones.model.Publicacion;
import com.example.publicaciones.service.PublicacionService;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/publicaciones")
public class PublicacionController {

    @Autowired
    private PublicacionService publicacionService;

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Publicacion>>> obtenerTodasLasPublicaciones() {
        List<Publicacion> publicaciones = publicacionService.obtenerTodasLasPublicaciones();
        List<EntityModel<Publicacion>> publicacionesResources = publicaciones.stream()
            .map(publicacion -> EntityModel.of(publicacion,
                linkTo(methodOn(this.getClass()).obtenerPublicacionPorId(publicacion.getId())).withSelfRel()
            ))
            .collect(Collectors.toList());

        CollectionModel<EntityModel<Publicacion>> model = CollectionModel.of(publicacionesResources,
            linkTo(methodOn(this.getClass()).obtenerTodasLasPublicaciones()).withSelfRel());

        return ResponseEntity.ok(model);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Publicacion>> obtenerPublicacionPorId(@PathVariable Long id) {
        Publicacion publicacion = publicacionService.obtenerPublicacionPorId(id);
        EntityModel<Publicacion> publicacionResource = EntityModel.of(publicacion,
            linkTo(methodOn(this.getClass()).obtenerPublicacionPorId(id)).withSelfRel(),
            linkTo(methodOn(this.getClass()).obtenerTodasLasPublicaciones()).withRel("publicaciones")
        );
        return ResponseEntity.ok(publicacionResource);
    }

    @PostMapping
    public ResponseEntity<EntityModel<Publicacion>> crearPublicacion(@RequestBody Publicacion publicacion) {
        Publicacion publicacionCreada = publicacionService.crearPublicacion(publicacion);
        EntityModel<Publicacion> publicacionResource = EntityModel.of(publicacionCreada,
            linkTo(methodOn(this.getClass()).obtenerPublicacionPorId(publicacionCreada.getId())).withSelfRel()
        );
        return ResponseEntity.ok(publicacionResource);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Publicacion>> actualizarPublicacion(@PathVariable Long id, @RequestBody Publicacion publicacion) {
        Publicacion publicacionActualizada = publicacionService.actualizarPublicacion(id, publicacion);
        EntityModel<Publicacion> publicacionResource = EntityModel.of(publicacionActualizada,
            linkTo(methodOn(this.getClass()).obtenerPublicacionPorId(id)).withSelfRel()
        );
        return ResponseEntity.ok(publicacionResource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPublicacion(@PathVariable Long id) {
        publicacionService.eliminarPublicacion(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/calificaciones")
    public ResponseEntity<Double> obtenerPromedioDeCalificaciones(@PathVariable Long id) {
        double promedio = publicacionService.calcularPromedioDeCalificaciones(id);
        return ResponseEntity.ok(promedio);
    }
}