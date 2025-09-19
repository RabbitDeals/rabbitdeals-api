package com.rabbit_deals.RabbitDeals.domain.dto;

import java.math.BigDecimal;

public record AnuncioCardDTO(
        String anuncioId,
        String titulo,
        String vendedorNome,
        String linkProduto,
        BigDecimal preco,
        String avaliacao,
        String imagens,
        String categoria
) {}