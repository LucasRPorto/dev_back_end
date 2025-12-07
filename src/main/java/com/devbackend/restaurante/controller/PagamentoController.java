package com.devbackend.restaurante.controller;

import com.devbackend.restaurante.service.PagamentoService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @PostMapping
    public ResponseEntity<String> fecharConta(@RequestBody PagamentoInput input) {
        pagamentoService.fecharConta(input.getIdConta());
        return ResponseEntity.ok("Conta fechada e mesa liberada com sucesso!");
    }

    public static class PagamentoInput {
        private Long idConta;

        public Long getIdConta() {
            return idConta;
        }

        public void setIdConta(Long idConta) {
            this.idConta = idConta;
        }
    }
}