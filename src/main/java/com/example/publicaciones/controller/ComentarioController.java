package com.example.publicaciones.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.publicaciones.model.Comentario;
import com.example.publicaciones.service.ComentarioService;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/comentarios")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    @GetMapping("/publicacion/{publicacionId}")
    public ResponseEntity<CollectionModel<EntityModel<Comentario>>> obtenerComentariosPorPublicacion(@PathVariable Long publicacionId) {
        List<Comentario> comentarios = comentarioService.obtenerComentariosPorPublicacion(publicacionId);
        List<EntityModel<Comentario>> comentariosResources = comentarios.stream()
            .map(comentario -> EntityModel.of(comentario,
                linkTo(methodOn(this.getClass()).obtenerComentarioPorId(comentario.getId())).withSelfRel(),
                linkTo(methodOn(PublicacionController.class).obtenerPublicacionPorId(publicacionId)).withRel("publicacion")
            ))
            .collect(Collectors.toList());

        CollectionModel<EntityModel<Comentario>> model = CollectionModel.of(comentariosResources,
            linkTo(methodOn(this.getClass()).obtenerComentariosPorPublicacion(publicacionId)).withSelfRel()
        );

        return ResponseEntity.ok(model);
    }

    @PostMapping("/publicacion/{publicacionId}")
    public ResponseEntity<EntityModel<Comentario>> crearComentario(@PathVariable Long publicacionId, @RequestBody Comentario comentario) {
        Comentario comentarioCreado = comentarioService.crearComentario(publicacionId, comentario);
        EntityModel<Comentario> comentarioResource = EntityModel.of(comentarioCreado,
            linkTo(methodOn(this.getClass()).obtenerComentarioPorId(comentarioCreado.getId())).withSelfRel(),
            linkTo(methodOn(PublicacionController.class).obtenerPublicacionPorId(publicacionId)).withRel("publicacion")
        );
        return ResponseEntity.ok(comentarioResource);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Comentario>> actualizarComentario(@PathVariable Long id, @RequestBody Comentario comentario) {
        Comentario comentarioActualizado = comentarioService.actualizarComentario(id, comentario);
        EntityModel<Comentario> comentarioResource = EntityModel.of(comentarioActualizado,
            linkTo(methodOn(this.getClass()).obtenerComentarioPorId(id)).withSelfRel()
        );
        return ResponseEntity.ok(comentarioResource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarComentario(@PathVariable Long id) {
        comentarioService.eliminarComentario(id);
        return ResponseEntity.noContent().build();
    }

    // Método auxiliar para obtener un enlace a un comentario específico
    public EntityModel<Comentario> obtenerComentarioPorId(Long id) {
        Comentario comentario = comentarioService.obtenerComentarioPorId(id);
        return EntityModel.of(comentario,
            linkTo(methodOn(this.getClass()).obtenerComentarioPorId(id)).withSelfRel()
        );
    }
}