package com.blackshark.sistemablog.web.controller;


import com.blackshark.sistemablog.domain.service.PublicacionServicioImp;
import com.blackshark.sistemablog.persistance.entity.Publicacion;
import com.blackshark.sistemablog.util.exception.PublicacionNotFoundException;
import com.blackshark.sistemablog.web.dto.PublicacionDTO;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api-publicaciones")
public class PublicacionControlller {

    @Autowired
    private PublicacionServicioImp publicacionServicioImp;

    @PostMapping
    public ResponseEntity<PublicacionDTO> guardarPublicacion(@RequestBody PublicacionDTO publicacionDTO) {
        try {
            return ResponseEntity.ok(this.publicacionServicioImp.crearPublicacion(publicacionDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Publicacion>> obtenerPublicaciones() {
        try {
            return ResponseEntity.ok(this.publicacionServicioImp.obtenerPublicaciones());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Publicacion> obtenerPublicacionPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(this.publicacionServicioImp.obtenerPublicacionPorId(id).get());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Publicacion> eliminarPublicacion(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(this.publicacionServicioImp.eliminarPublicacion(id));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<PublicacionDTO> actualizarPublicacion(@PathVariable Long id, @RequestBody PublicacionDTO publicacionDTO) {
        try {
            return ResponseEntity.ok(this.publicacionServicioImp.actualizarPublicacion(id, publicacionDTO).orElse(null));
        } catch (PublicacionNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}