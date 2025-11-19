package com.devbackend.restaurante.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemCardapioDTO {
    private Long id;
    private String nome;
    private String ingredientes;
    private Float preco;
    private String categoriaNome;
}