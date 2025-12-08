package com.devbackend.restaurante.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mesa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer numero;
    private Boolean disponivel;

    @ManyToOne
    @JoinColumn(name = "restaurante_id", nullable = true)
    private Restaurante restaurante;

    @ManyToOne
    @JoinColumn(name = "garcom_id")
    private Garcom garcom;
}