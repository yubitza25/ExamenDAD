package com.siesquen.infraccionservice.controller;

import com.siesquen.infraccionservice.entity.Infraccion;
import com.siesquen.infraccionservice.service.InfraccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/infracciones")
public class InfraccionController {

    @Autowired
    private InfraccionService infraccionService;

    @GetMapping
    public ResponseEntity<List<Infraccion>> getAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Infraccion> infracciones = infraccionService.findAll(pageable);
        return new ResponseEntity<>(infracciones, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Infraccion> findById(@PathVariable("id") int id) {
        Infraccion infraccion = infraccionService.findById(id);
        if (infraccion != null) {
            return ResponseEntity.ok(infraccion);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/buscar")
    public ResponseEntity<Infraccion> findByDni(@RequestParam("dni") String dni) {
        Infraccion infraccion = infraccionService.findByDni(dni);
        if (infraccion != null) {
            return ResponseEntity.ok(infraccion);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping
    public ResponseEntity<Infraccion> create(@RequestBody Infraccion infraccion) {
        Infraccion createdInfraccion = infraccionService.create(infraccion);
        return new ResponseEntity<>(createdInfraccion, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Infraccion> update(@PathVariable("id") int id, @RequestBody Infraccion infraccion) {
        infraccion.setId(id);
        Infraccion updatedInfraccion = infraccionService.update(infraccion);
        if (updatedInfraccion != null) {
            return ResponseEntity.ok(updatedInfraccion);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        boolean deleted = infraccionService.delete(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
