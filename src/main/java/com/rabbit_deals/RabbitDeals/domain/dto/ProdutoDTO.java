package com.rabbit_deals.RabbitDeals.domain.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record ProdutoDTO(
        @NotBlank String titulo,
        @NotBlank String vendedorNome,
        @NotBlank String linkProduto,
        @NotNull  BigDecimal preco,
        @NotBlank String avaliacao,
        @NotBlank String imagens,
        @NotBlank String categoria
) {}