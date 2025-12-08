package com.devbackend.restaurante.service;

import com.devbackend.restaurante.dto.MesaDTO;
import com.devbackend.restaurante.model.Mesa;
import com.devbackend.restaurante.repository.MesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MesaService {

    @Autowired
    private MesaRepository mesaRepository;

    // Método para o endpoint GET /mesas
    public List<MesaDTO> listarMesas() {
        List<Mesa> mesas = mesaRepository.findAll();

        return mesas.stream().map(mesa -> {
            MesaDTO dto = new MesaDTO();
            dto.setId(mesa.getId());
            dto.setNumero(mesa.getNumero());
            dto.setDisponivel(mesa.getDisponivel());

            if (mesa.getGarcom() != null) {
                dto.setNomeGarcomResponsavel(mesa.getGarcom().getNome());
            } else {
                dto.setNomeGarcomResponsavel("Sem garçom definido");
            }

            return dto;
        }).collect(Collectors.toList());
    }
}