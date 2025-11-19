package com.devbackend.restaurante.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemCardapio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String ingredientes;
    private Float preco;
    private Boolean disponivelNaCozinha;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;
}