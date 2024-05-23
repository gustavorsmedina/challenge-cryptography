# Desafio Criptografia 🕵️

Desafio proposto pela comunidade [Back-End Brasil](https://github.com/backend-br).  
Saiba mais: [Criptografia](https://github.com/backend-br/desafios/blob/master/cryptography/PROBLEM.md).
 

---

#### ☕ Tecnologias utilizadas:

- Java
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Jasypt
- Docker

---

## ⚙️ Passo a passo

#### 1 - Instale o [Docker](https://www.docker.com) em sua máquina. 

#### 2 - Abra o terminal na pasta raiz do projeto e execute o seguinte comando:

`docker compose up`

#### 3 - Crie o banco de dados necessário para a aplicação

- PostgreSQL:  
Crie um banco com o nome: db_transactions

#### 4 - Após terminar a configuração, inicie a aplicação e ela estará disponível em:

- http://localhost:8080/v1/transactions


#### 5 - Abra algum aplicativo para realizar suas requisições

- Algumas recomendações:  
[Insomnia](https://insomnia.rest/)  
[Postman](https://www.postman.com/)  
[Bruno](https://www.usebruno.com/)  

--- 

## 📨 Requisições

| Método | Url                            | Descrição                       | Corpo da requisição     |
| ------ | ------------------------------ | ------------------------------- | ----------------------- |
| POST   | /v1/transactions               | Crie uma nova transação.        | [JSON](#criartransacao) |
| GET    | /v1/transactions               | Busque todas transações.        |                         |
| GET    | /v1/transactions/{id}          | Busque uma transação por id.    |                         |
| PUT    | /v1/transactions/{id}          | Atualize uma transação por id.  | [JSON](#atualizartransacao) |
| DELETE | /v1/transactions/{id}          | Apague uma transação por id.    |                         |

---

## 📄 Corpo das requisições

##### <a id="criartransacao">/v1/transactions - Criando uma nova transação.</a>

```json
{
  "userDocument": "document",
  "creditCardToken": "credit card token",
  "value": 100 
}
```

##### <a id="atualizartransacao">/v1/transactions/{id} - Atualizando uma transação.</a>

```json
{
  "userDocument": "new document",
  "creditCardToken": "new credit card token",
  "value": 50 
}
```




