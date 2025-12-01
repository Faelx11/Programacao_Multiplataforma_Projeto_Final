# Serviço de Login

Este projeto implementa um serviço de autenticação simples que recebe credenciais de usuário e retorna um **Bearer Token** (JWT) quando as credenciais são válidas. O serviço faz parte do módulo 2 do projeto final de Programação Multiplataforma.

## Funcionamento

O endpoint `/login` aceita requisições `POST` com JSON contendo `username` e `password`. Para fins educacionais, as credenciais válidas são fixas:

* **username**: `admin`
* **password**: `password`

Se as credenciais forem corretas, o serviço gera um token JWT válido por 1 hora e o retorna no corpo da resposta.

## Executando

O serviço está configurado para rodar na porta **8081**. Ele não expõe sua porta diretamente quando executado via `docker-compose`; o acesso deve ser feito através do *API Gateway* deste módulo.

Para executar manualmente (fora do Docker):

```bash
mvn spring-boot:run
```

ou

```bash
mvn clean package -DskipTests
java -jar target/login-service-0.0.1-SNAPSHOT.jar
```

### Exemplo de requisição

```
POST /login HTTP/1.1
Host: localhost:8081
Content-Type: application/json

{
  "username": "admin",
  "password": "password"
}
```

Resposta de sucesso:

```json
{
  "token": "<token JWT>"
}
```

---

© 2025 – Módulo 2 do projeto final