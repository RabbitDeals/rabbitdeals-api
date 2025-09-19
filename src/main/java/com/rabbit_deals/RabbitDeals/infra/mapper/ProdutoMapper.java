package com.rabbit_deals.RabbitDeals.infra.mapper;

import com.rabbit_deals.RabbitDeals.domain.Produto;
import com.rabbit_deals.RabbitDeals.domain.dto.ProdutoDTO;

public class ProdutoMapper {
    public static Produto toEntity(ProdutoDTO dto) {
        Produto p = new Produto();
        p.setTitulo(dto.titulo());
        p.setVendedorNome(dto.vendedorNome());
        p.setLinkProduto(dto.linkProduto());
        p.setPreco(dto.preco());
        p.setAvaliacao(dto.avaliacao());
        p.setImagens(dto.imagens());
        p.setCategoria(dto.categoria());
        return p;
    }
}