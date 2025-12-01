# Módulo 2 – Serviço de Login com API Gateway

O módulo 2 apresenta duas aplicações Spring Boot trabalhadas em contêineres: um **serviço de login** e um **API Gateway**. O login fornece autenticação via usuário e senha e retorna um token JWT. O acesso ao serviço de login é feito exclusivamente pelo gateway, que expõe a rota `/login` para os clientes.

## Estrutura do repositório

* `login/` – Serviço responsável por validar credenciais e gerar tokens. Possui um endpoint `/login` que aceita requisições POST com `username` e `password`.
* `gateway/` – API Gateway construído com Spring Cloud Gateway. Redireciona chamadas ao `/login` para o serviço de login na rede interna.
* `docker-compose.yml` – Orquestra os dois serviços garantindo que apenas o gateway exponha porta para o host.

## Requisitos atendidos

| Requisito | Implementação |
|-----------|--------------|
| Retorno de token Bearer ao enviar usuário e senha válidos | O endpoint `/login` do serviço de login verifica credenciais estáticas (`admin` / `password`) e retorna um JWT se forem válidas. |
| API Gateway intermediando requisição | O contêiner `gateway` define uma rota no Spring Cloud Gateway direcionando `/login` para o serviço de login. |
| Contêiner da aplicação de Login sem expor porta direta | O serviço `login` não define mapeamento de portas em `docker-compose.yml`; somente o gateway expõe a porta 8080. |
| Inclusão de Dockerfile | Cada módulo (`login` e `gateway`) possui um Dockerfile multi-stage para construção da imagem. |

## Executando os serviços

Para subir o ambiente completo é preciso ter **Docker** e **Docker Compose**. A partir da pasta `modulo2`, execute:

```bash
docker compose up --build
```

Isso iniciará ambos os serviços. O gateway ficará acessível em `http://localhost:8080`.

### Autenticando

Faça uma requisição POST para `http://localhost:8080/login` com um JSON no corpo contendo as credenciais:

```json
{
  "username": "admin",
  "password": "password"
}
```

Em caso de sucesso, o corpo da resposta incluirá o token:

```json
{
  "token": "<jwt>"
}
```

---

© 2025 – Módulo 2 do projeto final