# Banking System — Versão 1

API REST desenvolvida com Java e Spring Boot para gerenciamento básico de contas bancárias.

## Funcionalidades

- Criar uma conta informando o nome do titular.
- Consultar os dados e o saldo de uma conta pelo ID.
- Depositar valores em uma conta existente.
- Sacar valores de uma conta existente.
- Retornar erros apropriados para operações inválidas.

## Tecnologias

- Java 25
- Spring Boot 4
- Spring Web
- Spring Data JPA
- Lombok
- H2 Database
- Maven

## Arquitetura

O projeto utiliza as seguintes camadas:

- `entity`: representa a conta bancária e suas regras financeiras.
- `repository`: realiza a comunicação entre a aplicação e o banco de dados.
- `service`: centraliza as operações do sistema.
- `controller`: expõe os endpoints da API.
- `dto`: representa os dados recebidos nas requisições.
- `exceptions`: contém exceções de negócio e o tratamento global de erros.

## Regras de negócio

### Criação da conta

- O nome do titular é obrigatório.
- Cada nova conta recebe um ID gerado automaticamente.
- O saldo inicial é `0.00`.

### Depósito

- O valor deve ser maior que zero.
- A conta precisa existir.

### Saque

- O valor deve ser maior que zero.
- A conta precisa existir.
- O valor não pode superar o saldo disponível.

## Executando o projeto

Na raiz do projeto, execute no Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

A API ficará disponível em:

```text
http://localhost:8080
```

## Endpoints

### Criar conta

```http
POST /accounts
Content-Type: application/json

{
  "holder": "Lucas"
}
```

Resposta esperada: `201 Created`.

### Consultar conta

```http
GET /accounts/{id}
```

Substitua `{id}` pelo identificador da conta.

Resposta esperada: `200 OK`.

### Realizar depósito

```http
POST /accounts/{id}/deposit
Content-Type: application/json

{
  "amount": 100.00
}
```

Resposta esperada: `200 OK`.

### Realizar saque

```http
POST /accounts/{id}/withdraw
Content-Type: application/json

{
  "amount": 50.00
}
```

Resposta esperada: `200 OK`.

## Respostas de erro

| Situação | Status HTTP |
|---|---|
| Valor inválido ou menor/igual a zero | `400 Bad Request` |
| Conta não encontrada | `404 Not Found` |
| Saldo insuficiente | `422 Unprocessable Entity` |

## Limitações desta versão

Esta versão não implementa:

- Login e autenticação.
- Transferências entre contas.
- Histórico ou consulta de extrato.
- Atualização e exclusão de contas.
