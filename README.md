# rabbitdeals-api

## Visão Geral
A **rabbitdeals-api** é uma API REST desenvolvida em Java com Spring Boot, projetada para gerenciar promoções e produtos de forma eficiente, escalável e fácil de integrar com outros sistemas.

---

## Funcionalidades Principais
- Cadastro, consulta, atualização e remoção de produtos
- Listagem paginada de produtos
- Integração com mensageria (RabbitMQ)
- Mapeamento DTO para entidades
- Configuração de CORS

---

## Estrutura do Projeto
```
src/main/java/com/rabbit_deals/RabbitDeals/
├── config/         # Configurações (CORS, RabbitMQ)
├── domain/         # Entidades de domínio e DTOs
├── infra/          # Repositórios e mapeadores
├── messaging/      # Listeners de mensageria
├── service/        # Regras de negócio
└── web/            # Controllers REST
```

---

## Como Rodar Localmente
1. **Pré-requisitos:**
   - Java 17+
   - Maven 3.8+
   - RabbitMQ rodando localmente (padrão: localhost:5672)

2. **Clone o repositório:**
   ```sh
   git clone <url-do-repo>
   cd rabbitdeals-api
   ```

3. **Configure o ambiente:**
   - Edite `src/main/resources/application.properties` conforme necessário.

4. **Execute a aplicação:**
   ```sh
   ./mvnw spring-boot:run
   ```
   Ou no Windows:
   ```sh
   mvnw.cmd spring-boot:run
   ```

---

## Endpoints Principais
- `GET /produtos` — Lista produtos (paginação disponível)
- `POST /produtos` — Cadastra novo produto
- `PUT /produtos/{id}` — Atualiza produto
- `DELETE /produtos/{id}` — Remove produto

---

## Mensageria (RabbitMQ)
- Listener: `AcademiaListener`
- Configuração: `RabbitConfig.java`
- Mensagens recebidas são processadas e integradas ao fluxo de produtos

---

## Testes
- Testes unitários e de integração em `src/test/java/com/rabbit_deals/RabbitDeals/`
- Para rodar:
   ```sh
   ./mvnw test
   ```

---

## Dicas Rápidas
- Use o profile `dev` para desenvolvimento local.
- O mapeamento DTO facilita integração com frontends.
- O projeto segue boas práticas de arquitetura em camadas.

---

## Contato
Dúvidas ou sugestões? Abra uma issue ou entre em contato com o mantenedor.
