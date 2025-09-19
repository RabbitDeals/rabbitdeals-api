package com.rabbit_deals.RabbitDeals.infra;

import com.rabbit_deals.RabbitDeals.domain.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigDecimal;
import java.util.Optional;

public interface ProdutoRepository extends MongoRepository<Produto, String> {
    Optional<Produto> findByLinkProduto(String linkProduto);

    Page<Produto> findByCategoriaIgnoreCase(String categoria, Pageable pageable);

    Page<Produto> findByCategoriaIgnoreCaseAndPrecoBetween(String categoria,
                                                           BigDecimal min,
                                                           BigDecimal max,
                                                           Pageable pageable
    );
}