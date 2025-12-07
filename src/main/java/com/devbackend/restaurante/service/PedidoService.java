package com.devbackend.restaurante.service;

import com.devbackend.restaurante.dto.ItemPedidoDTO;
import com.devbackend.restaurante.dto.PedidoDTO;
import com.devbackend.restaurante.model.*;
import com.devbackend.restaurante.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    private ItemCardapioRepository itemCardapioRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    // Método para o endpoint POST /pedidos
    @Transactional
    public PedidoDTO adicionarPedido(Long idConta, List<ItemPedidoDTO> itensDto) {
        Conta conta = contaRepository.findById(idConta)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));

        Pedido pedido = new Pedido();
        pedido.setConta(conta);
        pedido.setHorarioPedido(new Date());
        pedido.setNumero((int) (Math.random() * 1000));

        Cliente cliente = clienteRepository.findAll().stream().findFirst().orElse(null);
        pedido.setCliente(cliente);

        List<ItemPedido> itensEntidade = new ArrayList<>();

        for (ItemPedidoDTO itemDto : itensDto) {
            ItemCardapio produto = itemCardapioRepository.findAll().stream()
                    .filter(i -> i.getNome().equalsIgnoreCase(itemDto.getNomePrato()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado: " + itemDto.getNomePrato()));

            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setItemCardapio(produto);
            itemPedido.setQuantidade(itemDto.getQuantidade());
            itemPedido.setPedido(pedido);

            itensEntidade.add(itemPedido);
        }

        pedido.setItens(itensEntidade);
        Pedido pedidoSalvo = pedidoRepository.save(pedido);

        PedidoDTO retorno = new PedidoDTO();
        retorno.setId(pedidoSalvo.getId());
        retorno.setNumero(pedidoSalvo.getNumero());
        retorno.setHorarioPedido(pedidoSalvo.getHorarioPedido());

        if (pedidoSalvo.getCliente() != null) {
            retorno.setNomeCliente(pedidoSalvo.getCliente().getNome());
        }

        List<ItemPedidoDTO> itensRetorno = pedidoSalvo.getItens().stream().map(item -> {
            ItemPedidoDTO dto = new ItemPedidoDTO();
            dto.setNomePrato(item.getItemCardapio().getNome());
            dto.setQuantidade(item.getQuantidade());
            dto.setPrecoUnitario(item.getItemCardapio().getPreco());
            return dto;
        }).toList();

        retorno.setItens(itensRetorno);

        return retorno;
    }
}