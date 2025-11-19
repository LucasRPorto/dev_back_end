package com.devbackend.restaurante.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedidoDTO {
    private String nomePrato;
    private Float precoUnitario;
    private Float quantidade;
}