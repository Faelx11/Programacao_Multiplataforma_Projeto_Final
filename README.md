# Módulo 4 – Kafka com Produtor e Consumidores

Este módulo contém três aplicações **Spring Boot** que interagem via **Kafka**:

* **Produtor** – expõe um endpoint REST para envio de mensagens ao tópico `mensagens`.
* **Consumidor 1** – ouve o tópico e registra cada mensagem recebida.
* **Consumidor 2** – também ouve o mesmo tópico e registra as mensagens. Os consumidores possuem *group IDs* diferentes para que **ambos** recebam todas as mensagens.

O ambiente inclui um cluster Kafka com **três brokers** e **cinco partições** configuradas no tópico, garantindo resiliência e maior paralelismo.

## Requisitos atendidos

| Requisito | Implementação |
|-----------|--------------|
| Mensagem enviada pelo produtor é consumida por duas aplicações | Os consumidores utilizam group IDs diferentes (`consumer1-group` e `consumer2-group`), o que faz com que cada mensagem seja entregue a ambos. |
| Configuração correta de Group ID | Definido explicitamente nos listeners e nas propriedades das aplicações consumidoras. |
| Resiliência com três brokers Kafka | O `docker-compose.yml` define três serviços Kafka (`kafka1`, `kafka2`, `kafka3`) interligados a um Zookeeper, proporcionando tolerância a falhas. |
| Cinco partições | O produtor cria o tópico `mensagens` com 5 partições e replicação fator 3 via classe `KafkaConfig`. |

## Execução

Para levantar o ambiente completo é necessário **Docker** e **Docker Compose**. Navegue até a pasta `modulo4` e execute:

```bash
docker compose up --build
```

Os serviços subirão nas portas:

| Serviço     | Porta |
|-------------|-------|
| Produtor    | 8080  |
| Consumidor1 | 8081  |
| Consumidor2 | 8082  |
| Kafka brokers | 9092, 9093, 9094 |
| Zookeeper   | 2181 |

### Enviando mensagens

Com o cluster em execução, envie uma mensagem por meio do produtor:

```
curl "http://localhost:8080/send?message=Olá%20Kafka"
```

Tanto o **Consumidor 1** quanto o **Consumidor 2** exibirão no console uma linha semelhante a:

```
Consumidor 1 recebeu: Olá Kafka
```

e

```
Consumidor 2 recebeu: Olá Kafka
```

---

© 2025 – Módulo 4 do projeto final