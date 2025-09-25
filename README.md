# RabbitDeals API

> **O professor autorizou que o produtor seja em Python** para seguir o escopo padrão do projeto.  
> Estamos usando **Spring Boot** como consumidor 1 e **Python** como consumidor 2 (que envia mensagens para o Telegram).

## Integrantes

| Nome Completo                        | RA        |
|-------------------------------------|-----------|
| Alejandro Castor da Costa Fadim     | 01241115  |
| Felipe Ferreira                      | 01241160  |

---

## Descrição do Projeto

Este repositório contém o **Consumidor 1** do projeto:

- **Consumidor (Java/Spring Boot)**: Consome mensagens da fila RabbitMQ e processa os dados.

> O produtor (Python) envia mensagens diretamente para o RabbitMQ.

---

## Configuração do RabbitMQ com Docker Compose

O ambiente utiliza **Docker Compose** para orquestrar RabbitMQ e MongoDB, já configurados na raiz do projeto.

Para rodar:

```sh
docker-compose up --build
```

---

## Enviando Mensagens de Teste (via RabbitMQ)

> **Importante:** Não há endpoint HTTP para envio; as mensagens vão direto para a **exchange** via **AMQP** (Python).

- **Host RabbitMQ:** localhost  
- **Exchange:** `categoria_exchange`  
- **Credenciais:** admin / admin  
- **Protocolo:** AMQP  

**Exemplo de envio em Python:**

```python
import pika
import json

credentials = pika.PlainCredentials('admin', 'admin')
parameters = pika.ConnectionParameters(host='localhost', credentials=credentials)
connection = pika.BlockingConnection(parameters)
channel = connection.channel()

channel.exchange_declare(exchange='categoria_exchange', exchange_type='fanout')

produto_json = {
    "titulo": "Halter Ajustável 20kg",
    "vendedorNome": "Loja Fitness Pro",
    "linkProduto": "https://www.exemplo.com.br/halter-ajustavel-20kg",
    "preco": 299.90,
    "avaliacao": 4.8,
    "imagens": "https://www.exemplo.com.br/images/halter1.jpg",
    "categoria": "Academia"
}

channel.basic_publish(exchange='categoria_exchange', routing_key='', body=json.dumps(produto_json))
connection.close()
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
docker-compose up -d
```

3. **Verifique os produtos cadastrados**
- Abra no navegador:
```
http://localhost:8080/anuncios
```

