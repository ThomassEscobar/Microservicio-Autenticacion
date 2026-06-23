package com.consultoria.autenticacion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.consultoria.autenticacion.DTOs.AutenticacionDTO;
import com.consultoria.autenticacion.DTOs.AutenticacionResponseDTO;
import com.consultoria.autenticacion.service.AutenticacionService;

import jakarta.validation.Valid;
@RequestMapping("/autenticacion")
@RestController
public class AutenticacionController {

    @Autowired
    private AutenticacionService servi;

    @GetMapping
    public ResponseEntity<List<AutenticacionResponseDTO>> listar(){
        List<AutenticacionResponseDTO> lista = servi.findAll();
        if(lista.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(lista);
    }

    @PostMapping
    public ResponseEntity<AutenticacionDTO> save(@Valid @RequestBody AutenticacionDTO dto){
        try {
            AutenticacionDTO si = servi.sabe(dto);
            return ResponseEntity.ok(si);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/{username}")
    public ResponseEntity<List<AutenticacionResponseDTO>> buscarxUser(@PathVariable String username){
        try {
            List<AutenticacionResponseDTO> si = servi.findByUser(username);
            return ResponseEntity.ok(si);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
