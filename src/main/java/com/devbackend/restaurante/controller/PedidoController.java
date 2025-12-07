package com.devbackend.restaurante.controller;

import com.devbackend.restaurante.dto.ItemPedidoDTO;
import com.devbackend.restaurante.dto.PedidoDTO;
import com.devbackend.restaurante.service.PedidoService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<PedidoDTO> fazerPedido(@RequestBody PedidoInput input) {
        PedidoDTO pedido = pedidoService.adicionarPedido(input.getIdConta(), input.getItens());
        return ResponseEntity.status(201).body(pedido);
    }

    public static class PedidoInput {
        private Long idConta;
        private List<ItemPedidoDTO> itens;

        public Long getIdConta() {
            return idConta;
        }

        public void setIdConta(Long idConta) {
            this.idConta = idConta;
        }

        public List<ItemPedidoDTO> getItens() {
            return itens;
        }

        public void setItens(List<ItemPedidoDTO> itens) {
            this.itens = itens;
        }
    }
}