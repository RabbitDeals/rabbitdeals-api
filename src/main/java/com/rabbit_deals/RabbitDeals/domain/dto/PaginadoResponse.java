package com.rabbit_deals.RabbitDeals.domain.dto;

import java.util.List;

public record PaginadoResponse<T>(
        List<T> anuncios,
        int page,
        int size,
        long totalElements,
        int totalPages,
        boolean first,
        boolean last,
        boolean hasNext,
        boolean hasPrevious
) {}

