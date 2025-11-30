package com.devbackend.restaurante.repository;

import com.devbackend.restaurante.model.ItemCardapio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemCardapioRepository extends JpaRepository<ItemCardapio, Long> {
}