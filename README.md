

---

# RabbitDeals API

## Integrantes

| Nome Completo                        | RA        |
|----------------------                |-----------|
| Alejandro castor da costa fadim      | 01241115  |
| Felipe ferreira                      | 01241160  |

---

## Descrição dos Projetos

Este repositório contém duas aplicações:

- **Produtor**: Responsável por receber requisições HTTP e publicar mensagens na fila RabbitMQ.
- **Consumidor**: Responsável por consumir as mensagens da fila RabbitMQ e processá-las.

---

## Configuração do RabbitMQ com Docker Compose

O ambiente utiliza o **Docker Compose** para orquestrar os serviços, incluindo o RabbitMQ.

### Exemplo de `docker-compose.yml`

```yaml
version: '3.8'

services:
  rabbitmq:
    image: rabbitmq:3-management
    ports:
      - "5672:5672"
      - "15672:15672"
    environment:
      RABBITMQ_DEFAULT_USER: guest
      RABBITMQ_DEFAULT_PASS: guest

  produtor:
    build: ./produtor
    depends_on:
      - rabbitmq

  consumidor:
    build: ./consumidor
    depends_on:
      - rabbitmq
```

**Obs:** Ajuste os paths dos serviços conforme sua estrutura.

---

## Como Enviar uma Mensagem de Teste (Endpoint do Produtor)

- **URL do endpoint de publicação:**
  ```
  POST http://localhost:8080/api/publish
  ```
- **Método HTTP:** `POST`
- **Headers necessários:**
  ```
  Content-Type: application/json
  ```
- **Exemplo de JSON a ser enviado:**
  ```json
  {
    "produto": "Celular",
    "preco": 1500.00
  }
  ```

---

## Passo a Passo para Subir o Ambiente

1. **Clone o repositório**
    ```sh
    git clone https://github.com/RabbitDeals/rabbitdeals-api.git
    cd rabbitdeals-api
    ```

2. **Suba o ambiente com Docker Compose**
    ```sh
    docker compose up -d
    ```

3. **Acesse o RabbitMQ (caso queira visualizar as filas)**
    - URL: [http://localhost:15672/](http://localhost:15672/)
    - Usuário: `guest`
    - Senha: `guest`

4. **Teste o endpoint do produtor**
    - Use o Postman, Insomnia ou curl:
    ```sh
    curl -X POST http://localhost:8080/api/publish \
      -H "Content-Type: application/json" \
      -d '{"produto":"Celular","preco":1500.00}'
    ```

---

## Como Verificar a Mensagem no Consumidor

- O **Consumidor** expõe um endpoint para consultar as mensagens processadas:

    ```
    GET http://localhost:8081/api/mensagens
    ```

- **Exemplo de retorno esperado:**
    ```json
    [
      {
        "produto": "Celular",
        "preco": 1500.00,
        "status": "Recebido"
      }
    ]
    ```

---

## Observações Importantes

- **Todos os passos acima devem ser seguidos para validação da entrega.**
- A ausência deste arquivo README.md **com nomes/RAs, explicação dos projetos e instruções** resultará em nota 0.

---

Se precisar de ajustes para os endpoints, portas, nomes de campos ou exemplos, só avisar!
