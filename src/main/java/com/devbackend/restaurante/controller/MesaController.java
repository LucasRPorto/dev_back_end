package com.devbackend.restaurante.controller;

import com.devbackend.restaurante.dto.MesaDTO;
import com.devbackend.restaurante.service.MesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mesas")
public class MesaController {

    @Autowired
    private MesaService mesaService;

    @GetMapping
    public List<MesaDTO> listarMesas() {
        return mesaService.listarMesas();
    }
}