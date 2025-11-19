package com.devbackend.restaurante.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MesaDTO {
    private Long id;
    private Integer numero;
    private Boolean disponivel;
    private String nomeGarcomResponsavel;
}