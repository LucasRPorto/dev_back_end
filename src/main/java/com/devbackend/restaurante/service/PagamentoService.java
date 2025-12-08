package com.devbackend.restaurante.service;

import com.devbackend.restaurante.model.Conta;
import com.devbackend.restaurante.model.Dinheiro;
import com.devbackend.restaurante.model.Mesa;
import com.devbackend.restaurante.model.Pagamento;
import com.devbackend.restaurante.repository.ContaRepository;
import com.devbackend.restaurante.repository.MesaRepository;
import com.devbackend.restaurante.repository.PagamentoRepository;
import com.devbackend.restaurante.service.exception.RecursoNaoEncontradoException;
import com.devbackend.restaurante.service.exception.RegraDeNegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PagamentoService {

    @Autowired private PagamentoRepository pagamentoRepository;
    @Autowired private ContaRepository contaRepository;
    @Autowired private MesaRepository mesaRepository;

    @Transactional
    public void fecharConta(Long idConta) {
        Conta conta = contaRepository.findById(idConta)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Conta não encontrada com o ID: " + idConta));
        if (conta.getPagamento() != null) {
            throw new RegraDeNegocioException("Esta conta já foi paga e encerrada anteriormente!");
        }

        Pagamento pagamento = new Dinheiro();
        pagamento.setConta(conta);
        pagamentoRepository.save(pagamento);

        conta.setPagamento(pagamento);
        contaRepository.save(conta);

        Mesa mesa = conta.getMesa();
        mesa.setDisponivel(true);
        mesaRepository.save(mesa);
    }
}