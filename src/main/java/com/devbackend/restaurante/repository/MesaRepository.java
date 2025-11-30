package com.devbackend.restaurante.repository;

import com.devbackend.restaurante.model.Mesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MesaRepository extends JpaRepository<Mesa, Long> {
    List<Mesa> findByDisponivel(Boolean disponivel);
}