package com.rabbit_deals.RabbitDeals.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbit_deals.RabbitDeals.config.RabbitConfig;
import com.rabbit_deals.RabbitDeals.domain.dto.ProdutoDTO;
import com.rabbit_deals.RabbitDeals.service.ProdutoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AcademiaListener {

    private final ProdutoService service;
    private final ObjectMapper mapper;

    public AcademiaListener(ProdutoService service, ObjectMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @RabbitListener(queues = RabbitConfig.QUEUE_ACA)
    public void onAcademiaMessage(@Payload String body) {
        try {
            log.info("[ACADEMIA] RAW payload: {}", body);
            ProdutoDTO dto = mapper.readValue(body, ProdutoDTO.class);
            service.upsert(dto);
            log.info("[ACADEMIA] Upsert OK: {}", dto.linkProduto());
        } catch (Exception e) {
            log.error("[ACADEMIA] Falha processando mensagem", e);
        }
    }
}