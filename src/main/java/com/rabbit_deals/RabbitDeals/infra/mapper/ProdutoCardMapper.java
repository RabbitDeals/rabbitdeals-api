package com.rabbit_deals.RabbitDeals.infra.mapper;

import com.rabbit_deals.RabbitDeals.domain.Produto;
import com.rabbit_deals.RabbitDeals.domain.dto.AnuncioCardDTO;

public class ProdutoCardMapper {
    public static AnuncioCardDTO toCard(Produto p) {
        return new AnuncioCardDTO(
                p.getId(),
                p.getTitulo(),
                p.getVendedorNome(),
                p.getLinkProduto(),
                p.getPreco(),
                normalizarAvaliacao(p.getAvaliacao()),
                p.getImagens(),
                p.getCategoria()
        );
    }

    private static String normalizarAvaliacao(String a) {
        if (a == null) return null;
        try {
            var nota = a.replaceAll(".*?([0-9],[0-9]).*", "$1");
            var qtd  = a.replaceAll(".*?\\(([0-9\\.]+) avaliações\\).*", "$1");
            if (nota.equals(a) || qtd.equals(a)) return a; // fallback
            return nota + " (" + qtd + ")";
        } catch (Exception e) {
            return a;
        }
    }
}