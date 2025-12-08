package com.devbackend.restaurante.dto;

import lombok.Data;
import java.util.List;

@Data
public class ReciboDTO {
    private Long idConta;
    private String nomeCliente;
    private String nomeMesa;
    private List<ItemResumoDTO> itensConsumidos;
    private Double valorTotal;
    private String dataPagamento;
}