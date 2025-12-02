# Módulo 3 – Função Lambda com Kafka

O terceiro módulo propõe a criação de uma **função AWS Lambda** (em JavaScript) que consome mensagens de um tópico Kafka e registra cada uma delas no console. Em seguida, essa função é empacotada em uma imagem Docker e publicada no DockerHub por meio de uma GitHub Action.

## Estrutura

| Arquivo | Descrição |
|--------|-----------|
| `index.js` | Contém a função `handler` que recebe um evento no formato de trigger do AWS MSK e imprime as mensagens recebidas. |
| `Dockerfile` | Define a imagem baseada em `public.ecr.aws/lambda/nodejs:18` utilizada para executar a função como contêiner. |
| `.github/workflows/docker-image.yml` | Workflow do GitHub Actions responsável por construir e enviar a imagem ao DockerHub sempre que houver push para a branch `main` dentro do módulo 3. |

## Executando localmente

Para testar a função localmente dentro de um contêiner Docker:

```bash
docker build -t modulo3-lambda:latest .

# Exemplo de execução local usando a interface do AWS Lambda para contêiner
docker run -p 9000:8080 modulo3-lambda:latest

# Em outro terminal, envie um evento de teste
curl -X POST "http://localhost:9000/2015-03-31/functions/function/invocations" -H "Content-Type: application/json" -d "{\"records\":{\"meu-topico-1\":[{\"value\":\"SGVsbG8gS2Fma2Eh\"}]}}"

```

O contêiner imprimirá no console: `A mensagem chegou: mensagem de exemplo!`.

## Publicação no DockerHub

O workflow em `.github/workflows/docker-image.yml` automatiza a construção e envio da imagem. Para utilizar:

1. Crie repositório no GitHub e adicione os arquivos deste módulo.
2. Configure os *secrets* `DOCKERHUB_USERNAME` e `DOCKERHUB_TOKEN` nas configurações do repositório.
3. Faça push para a branch `main`. O GitHub Actions irá construir a imagem e publicá-la em `docker.io/&lt;seu usuário&gt;/modulo3-lambda:latest`.

---

© 2025 – Módulo 3 do projeto final