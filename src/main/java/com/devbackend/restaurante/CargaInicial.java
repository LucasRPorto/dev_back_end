package com.devbackend.restaurante;

import com.devbackend.restaurante.model.Garcom;
import com.devbackend.restaurante.model.ItemCardapio;
import com.devbackend.restaurante.model.Mesa;
import com.devbackend.restaurante.model.Restaurante;
import com.devbackend.restaurante.repository.GarcomRepository;
import com.devbackend.restaurante.repository.ItemCardapioRepository;
import com.devbackend.restaurante.repository.MesaRepository;
import com.devbackend.restaurante.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Configuration
public class CargaInicial implements CommandLineRunner {

    @Autowired private MesaRepository mesaRepository;
    @Autowired private ItemCardapioRepository itemCardapioRepository;
    @Autowired private RestauranteRepository restauranteRepository;
    @Autowired private GarcomRepository garcomRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        // Criar o Restaurante
        Restaurante restaurante = null;
        if (restauranteRepository.count() == 0) {
            restaurante = new Restaurante();
            restaurante.setNome("Restaurante da Pós");
            restaurante = restauranteRepository.save(restaurante);
        } else {
            restaurante = restauranteRepository.findAll().get(0);
        }

        // Criar Garçons
        Garcom g1 = null;
        Garcom g2 = null;

        if (garcomRepository.count() == 0) {
            g1 = new Garcom();
            g1.setNome("Lucas Rodrigues");
            g1.setLogin("lucas");
            g1.setSenha("123");
            g1 = garcomRepository.save(g1);

            g2 = new Garcom();
            g2.setNome("Carlos Henrique");
            g2.setLogin("carlos");
            g2.setSenha("123");
            g2 = garcomRepository.save(g2);
        } else {
            g1 = garcomRepository.findAll().get(0);
            g2 = garcomRepository.findAll().get(1);
        }

        // Criar Mesas
        if (mesaRepository.count() == 0) {
            // Lucas cuida das mesas 1, 2 e 3
            Mesa m1 = new Mesa(); m1.setNumero(1); m1.setDisponivel(true); m1.setRestaurante(restaurante); m1.setGarcom(g1);
            Mesa m2 = new Mesa(); m2.setNumero(2); m2.setDisponivel(true); m2.setRestaurante(restaurante); m2.setGarcom(g1);
            Mesa m3 = new Mesa(); m3.setNumero(3); m3.setDisponivel(true); m3.setRestaurante(restaurante); m3.setGarcom(g1);

            // Carlos cuida das mesas 4 e 5
            Mesa m4 = new Mesa(); m4.setNumero(4); m4.setDisponivel(true); m4.setRestaurante(restaurante); m4.setGarcom(g2);
            Mesa m5 = new Mesa(); m5.setNumero(5); m5.setDisponivel(true); m5.setRestaurante(restaurante); m5.setGarcom(g2);

            mesaRepository.saveAll(Arrays.asList(m1, m2, m3, m4, m5));
        }

        // Criar Itens do Cardápio
        if (itemCardapioRepository.count() == 0) {
            ItemCardapio i1 = new ItemCardapio(); i1.setNome("Coca Cola"); i1.setIngredientes("Lata 350ml"); i1.setPreco(5.0f); i1.setDisponivelNaCozinha(true);
            ItemCardapio i2 = new ItemCardapio(); i2.setNome("Hamburguer"); i2.setIngredientes("Carne 180g"); i2.setPreco(25.0f); i2.setDisponivelNaCozinha(true);
            ItemCardapio i3 = new ItemCardapio(); i3.setNome("Batata Frita"); i3.setIngredientes("Porção"); i3.setPreco(12.0f); i3.setDisponivelNaCozinha(true);

            itemCardapioRepository.saveAll(Arrays.asList(i1, i2, i3));
        }
    }
}