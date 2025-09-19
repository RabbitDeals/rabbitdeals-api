package com.rabbit_deals.RabbitDeals.web;

import com.rabbit_deals.RabbitDeals.domain.Produto;
import com.rabbit_deals.RabbitDeals.domain.dto.AnuncioCardDTO;
import com.rabbit_deals.RabbitDeals.domain.dto.PaginadoResponse;
import com.rabbit_deals.RabbitDeals.infra.mapper.ProdutoCardMapper;
import com.rabbit_deals.RabbitDeals.service.ProdutoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/anuncios")
public class ProdutoController {

    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @GetMapping()
    public PaginadoResponse<AnuncioCardDTO> listar(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size,
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) BigDecimal precoMin,
            @RequestParam(required = false) BigDecimal precoMax,
            @RequestParam(required = false, defaultValue = "titulo,asc") String sort
    ) {
        var parts = sort.split(",", 2);
        var sortObj = (parts.length == 2)
                ? org.springframework.data.domain.Sort.by(org.springframework.data.domain.Sort.Direction.fromString(parts[1]), parts[0])
                : org.springframework.data.domain.Sort.by(parts[0]);

        var pageable = org.springframework.data.domain.PageRequest.of(page, size, sortObj);

        org.springframework.data.domain.Page<Produto> pageRes;

        if (categoria != null && precoMin != null && precoMax != null) {
            pageRes = service.listarPorCategoriaEPreco(categoria, precoMin, precoMax, pageable);
        } else if (categoria != null) {
            pageRes = service.listarPorCategoria(categoria, pageable);
        } else {
            pageRes = service.listarTodos(pageable);
        }

        var lista = pageRes.getContent().stream()
                .map(ProdutoCardMapper::toCard)
                .toList();

        return new PaginadoResponse<>(
                lista,
                pageRes.getNumber(),
                pageRes.getSize(),
                pageRes.getTotalElements(),
                pageRes.getTotalPages(),
                pageRes.isFirst(),
                pageRes.isLast(),
                pageRes.hasNext(),
                pageRes.hasPrevious()
        );
    }
}