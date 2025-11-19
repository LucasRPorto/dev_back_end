package com.devbackend.restaurante.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {
    private Long id;
    private Integer numero;
    private Date horarioPedido;
    private String nomeCliente;
    private List<ItemPedidoDTO> itens;
}