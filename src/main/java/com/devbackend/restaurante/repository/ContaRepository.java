package com.devbackend.restaurante.repository;

import com.devbackend.restaurante.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {
    Optional<Conta> findByMesaIdAndPagamentoIsNull(Long mesaId);
}