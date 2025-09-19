package com.rabbit_deals.RabbitDeals.service;

import com.rabbit_deals.RabbitDeals.domain.Produto;
import com.rabbit_deals.RabbitDeals.domain.dto.ProdutoDTO;
import com.rabbit_deals.RabbitDeals.infra.ProdutoRepository;
import com.rabbit_deals.RabbitDeals.infra.mapper.ProdutoMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProdutoService {

    private final ProdutoRepository repo;
    private volatile Produto lastAcademia;

    public ProdutoService(ProdutoRepository repo) {
        this.repo = repo;
    }

    public Produto upsert(ProdutoDTO dto) {
        Produto entity = repo.findByLinkProduto(dto.linkProduto())
                .map(p -> {
                    p.setTitulo(dto.titulo());
                    p.setVendedorNome(dto.vendedorNome());
                    p.setPreco(dto.preco());
                    p.setAvaliacao(dto.avaliacao());
                    p.setImagens(dto.imagens());
                    p.setCategoria(dto.categoria());
                    return p;
                })
                .orElseGet(() -> ProdutoMapper.toEntity(dto));

        Produto saved = repo.save(entity);

        if ("Academia".equalsIgnoreCase(saved.getCategoria())) {
            lastAcademia = saved;
        }
        return saved;
    }

    public Page<Produto> listarTodos(Pageable pg) {
        return repo.findAll(pg);
    }

    public Page<Produto> listarPorCategoria(String categoria, Pageable pg) {
        return repo.findByCategoriaIgnoreCase(categoria, pg);
    }

    public Page<Produto> listarPorCategoriaEPreco(String categoria, BigDecimal min, BigDecimal max, Pageable pg) {
        return repo.findByCategoriaIgnoreCaseAndPrecoBetween(categoria, min, max, pg);
    }

    public Produto getLastAcademia() { return lastAcademia; }

}