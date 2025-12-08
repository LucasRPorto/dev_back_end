package com.devbackend.restaurante.dto;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class ItemResumoDTO {
    private String nomePrato;
    private Integer quantidade;
    private Float precoUnitario;
    private Double subtotal;
}