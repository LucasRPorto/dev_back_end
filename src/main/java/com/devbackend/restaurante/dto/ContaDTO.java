package com.devbackend.restaurante.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContaDTO {
    private Long id;
    private String nomeMesa;
    private List<PedidoDTO> pedidos;
    private Double valorTotal;
    private String statusPagamento;
}