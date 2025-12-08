package com.devbackend.restaurante.repository;

import com.devbackend.restaurante.model.Garcom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GarcomRepository extends JpaRepository<Garcom, Long> {
}