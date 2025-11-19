# Módulo 1 – API de Pessoas

Este repositório contém a implementação do módulo 1 do projeto final de Programação Multiplataforma. A aplicação é uma API REST desenvolvida com **Spring Boot 3** que realiza operações de CRUD sobre a entidade **Pessoa** e segue princípios de arquitetura limpa:

* **Camada de apresentação (Controller)** – expõe os endpoints REST.
* **Camada de serviço** – contém as regras de negócio.
* **Camada de persistência (Repository)** – encapsula o acesso ao banco de dados via Spring Data JPA.

## Funcionalidades

* CRUD completo de pessoas (criar, listar, obter por ID, atualizar e excluir).
* Listagem paginada (10 itens por página) retornando apenas pessoas com o atributo **ativo = true**.
* Persistência em banco de dados PostgreSQL.
* Logging configurado para envio de logs ao **Graylog** via GELF.
* Contêiner Docker da aplicação e **docker‑compose** com todos os serviços necessários (PostgreSQL, MongoDB, OpenSearch e Graylog).

### Entidade Pessoa

| Campo         | Tipo     | Descrição                       |
|---------------|----------|---------------------------------|
| `id`          | Long     | Identificador auto‑gerado       |
| `nome`        | String   | Nome da pessoa (obrigatório)    |
| `dtNascimento`| LocalDate| Data de nascimento (obrigatório)|
| `ativo`       | Boolean  | Indica se o registro está ativo |

## Executando o projeto

É necessário ter **Docker** e **Docker Compose** instalados. A aplicação é executada totalmente em contêineres.

1. **Clonar** o repositório.
2. Na pasta `modulo1`, construir e subir os serviços:

   ```bash
   docker compose up --build
   ```

   Isso criará e iniciará os serviços: PostgreSQL, MongoDB, OpenSearch, Graylog e a aplicação Spring Boot.

3. A API estará acessível em `http://localhost:8080/pessoas`. A interface web do Graylog estará em `http://localhost:9000` (usuário **admin**, senha **admin**).

## Testando a API

Alguns exemplos de chamadas HTTP:

* **Listar pessoas ativas** (primeira página, 10 itens):

  ```
  GET http://localhost:8080/pessoas
  ```

* **Criar uma pessoa**:

  ```
  POST http://localhost:8080/pessoas
  Content-Type: application/json

  {
    "nome": "João da Silva",
    "dtNascimento": "1990-05-12",
    "ativo": true
  }
  ```

* **Buscar por ID**:

  ```
  GET http://localhost:8080/pessoas/1
  ```

## SonarQube e qualidade de código

Para avaliar a qualidade do código, recomenda-se configurar um servidor **SonarQube** e executar o **Sonar Scanner** apontando para este repositório. O projeto foi estruturado com atenção a boas práticas e pode ser analisado conforme instruções da disciplina.

---

© 2025 – Projeto final de Programação Multiplataforma