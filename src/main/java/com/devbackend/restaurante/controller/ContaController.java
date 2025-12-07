package com.devbackend.restaurante.controller;

import com.devbackend.restaurante.dto.ContaDTO;
import com.devbackend.restaurante.service.ContaService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contas")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @PostMapping
    public ResponseEntity<ContaDTO> abrirConta(@RequestBody ContaInput input) {
        ContaDTO conta = contaService.abrirConta(input.getIdMesa(), input.getNomeCliente());
        return ResponseEntity.status(201).body(conta);
    }

    @Data
    public static class ContaInput {
        private Long idMesa;
        private String nomeCliente;
    }
}