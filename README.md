# RabbitDeals API

## Integrantes

| Nome Completo                        | RA        |
|-------------------------------------|-----------|
| Alejandro Castor da Costa Fadim     | 01241115  |
| Felipe Ferreira                      | 01241160  |

---

## Descrição dos Projetos

Este repositório contém o consumidor número 1 do nosso projeto feito em Java:

- **Consumidor**: Responsável por consumir as mensagens da fila RabbitMQ e processá-las.

---

## Configuração do RabbitMQ com Docker Compose

O ambiente utiliza o **Docker Compose** para orquestrar os serviços, incluindo o RabbitMQ, tudo isso já está configurado na raiz do projeto.

Para executar o projeto basta rodar o seguinte comando na sua máquina (é necessário ter o Docker Desktop instalado e rodando):

```sh
docker-compose up --build
```

---

## Como Enviar uma Mensagem de Teste (via RabbitMQ)

> **Observação:** Esta aplicação **não expõe um endpoint HTTP**. As mensagens são enviadas diretamente para a exchange do RabbitMQ usando o protocolo **AMQP**.

- **Host RabbitMQ:** localhost  
- **Exchange:** categoria_exchange  
- **Credenciais:** admin / admin  
- **Protocolo:** AMQP  

- **Exemplo de envio em Python (ilustrativo):**

```python
import pika
import json

credentials = pika.PlainCredentials('admin', 'admin')
parameters = pika.ConnectionParameters(host='localhost', credentials=credentials)
connection = pika.BlockingConnection(parameters)
channel = connection.channel()

channel.exchange_declare(exchange='categoria_exchange', exchange_type='fanout')

message = {"produto": "Celular", "preco": 1500.00}
channel.basic_publish(exchange='categoria_exchange', routing_key='', body=json.dumps(message))

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

3. **Teste o endpoint do produtor**
- Acesse no navegador a URL para verificar os produtos cadastrados no sistema:
```
http://localhost:8080/anuncios
```
