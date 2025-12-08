package com.devbackend.restaurante.service;

import com.devbackend.restaurante.dto.ContaDTO;
import com.devbackend.restaurante.model.Cliente;
import com.devbackend.restaurante.model.Conta;
import com.devbackend.restaurante.model.Mesa;
import com.devbackend.restaurante.repository.ClienteRepository;
import com.devbackend.restaurante.repository.ContaRepository;
import com.devbackend.restaurante.repository.MesaRepository;
import com.devbackend.restaurante.service.exception.RecursoNaoEncontradoException;
import com.devbackend.restaurante.service.exception.RegraDeNegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    private MesaRepository mesaRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    public ContaDTO abrirConta(Long idMesa, String nomeCliente) {
        Mesa mesa = mesaRepository.findById(idMesa)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Mesa não encontrada com o ID: " + idMesa));
        if (!mesa.getDisponivel()) {
            throw new RegraDeNegocioException("Esta mesa já está ocupada!");
        }

        mesa.setDisponivel(false);
        mesaRepository.save(mesa);

        Cliente cliente = new Cliente();
        cliente.setNome(nomeCliente);
        cliente.setHoraChegada(new Date());
        clienteRepository.save(cliente);

        Conta conta = new Conta();
        conta.setMesa(mesa);
        conta.setNome("Conta da Mesa " + mesa.getNumero());

        Conta contaSalva = contaRepository.save(conta);

        ContaDTO dto = new ContaDTO();
        dto.setId(contaSalva.getId());
        dto.setNomeMesa("Mesa " + mesa.getNumero());
        dto.setStatusPagamento("ABERTA");

        return dto;
    }
}