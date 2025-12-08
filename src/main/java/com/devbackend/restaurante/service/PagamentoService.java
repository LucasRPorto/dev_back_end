package com.devbackend.restaurante.service;

import com.devbackend.restaurante.dto.ItemResumoDTO;
import com.devbackend.restaurante.dto.ReciboDTO;
import com.devbackend.restaurante.model.*;
import com.devbackend.restaurante.repository.*;
import com.devbackend.restaurante.service.exception.RecursoNaoEncontradoException;
import com.devbackend.restaurante.service.exception.RegraDeNegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class PagamentoService {

    @Autowired private PagamentoRepository pagamentoRepository;
    @Autowired private ContaRepository contaRepository;
    @Autowired private MesaRepository mesaRepository;

    @Transactional
    public ReciboDTO fecharConta(Long idConta) {
        Conta conta = contaRepository.findById(idConta)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Conta não encontrada com o ID: " + idConta));

        if (conta.getPagamento() != null) {
            throw new RegraDeNegocioException("Esta conta já foi paga e encerrada anteriormente!");
        }

        List<ItemResumoDTO> listaItens = new ArrayList<>();
        double totalConta = 0.0;

        if (conta.getPedidos() != null) {
            for (Pedido pedido : conta.getPedidos()) {
                for (ItemPedido item : pedido.getItens()) {
                    double subtotal = item.getQuantidade() * item.getItemCardapio().getPreco();
                    totalConta += subtotal;

                    listaItens.add(new ItemResumoDTO(
                            item.getItemCardapio().getNome(),
                            Math.round(item.getQuantidade()),
                            item.getItemCardapio().getPreco(),
                            subtotal
                    ));
                }
            }
        }

        Pagamento pagamento = new Dinheiro();
        pagamento.setConta(conta);
        pagamentoRepository.save(pagamento);

        conta.setPagamento(pagamento);
        contaRepository.save(conta);

        Mesa mesa = conta.getMesa();
        mesa.setDisponivel(true);
        mesaRepository.save(mesa);

        ReciboDTO recibo = new ReciboDTO();
        recibo.setIdConta(conta.getId());
        recibo.setNomeMesa("Mesa " + mesa.getNumero());

        String nomeCliente = "Cliente";
        if (conta.getPedidos() != null && !conta.getPedidos().isEmpty() && conta.getPedidos().get(0).getCliente() != null) {
            nomeCliente = conta.getPedidos().get(0).getCliente().getNome();
        }
        recibo.setNomeCliente(nomeCliente);

        recibo.setItensConsumidos(listaItens);
        recibo.setValorTotal(totalConta);
        recibo.setDataPagamento(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));

        return recibo;
    }
}