# Trabalho Final Back_End

**Descrição:** Projeto desenvolvido para a disciplina de Back-end da Residência de Tecnologia UFG/TJ (Tribunal de Justiça de Goiás). O objetivo foi implementar uma API RESTful robusta para o gerenciamento do fluxo de atendimento de um restaurante, baseada em um diagrama de classes UML complexo.

---

## 👨‍🎓 Alunos

* **Carlos Henrique Silva Bispo Rodrigues** - Matrícula: 2025200222
* **Lucas Rodrigues Porto** - Matrícula: 2025200247

---

## 🚀 Sobre o Projeto

Este sistema gerencia o ciclo de vida de um cliente dentro de um restaurante, desde a verificação de mesas disponíveis até o pagamento e emissão do recibo.

A aplicação foi construída com foco em:
* **Integridade de Dados:** Validações de regras de negócio (ex: não abrir conta em mesa ocupada).
* **Tratamento de Erros:** Respostas HTTP adequadas (404 para não encontrado, 409 para conflitos de regra).
* **Arquitetura em Camadas:** Separação clara entre Controller, Service, Repository e Model/DTO.

---

## 🛠️ Tecnologias Utilizadas

* **Java 17** (LTS)
* **Spring Boot 3** (Web, Data JPA)
* **MySQL 8** (Banco de Dados)
* **Hibernate** (ORM e DDL Auto)
* **Lombok** (Redução de boilerplate)
* **Maven** (Gerenciamento de dependências)

---

## ⚙️ Configuração e Instalação

### Pré-requisitos
* JDK 17 instalado.
* MySQL Server rodando na porta 3306.

### Passo a Passo

1.  **Clone o repositório:**


2.  **Configurar o Banco de Dados:**
    * Crie um schema vazio no MySQL:
        ```sql
        CREATE DATABASE restaurante_db;
        ```
    * Abra o arquivo `src/main/resources/application.properties` e ajuste sua senha:
        ```properties
        spring.datasource.username=root
        spring.datasource.password=senha
        ```

3.  **Executar a Aplicação:**
    * A aplicação utiliza o **Spring Boot**. Basta rodar a classe principal `DevBackEndApplication.java`
    * **Carga Inicial:** Ao iniciar, o sistema popula automaticamente o banco com:
        * 1 Restaurante
        * 2 Garçons 
        * 5 Mesas
        * Itens do Cardápio (Hamburguer, Coca Cola, Batata Frita).

---

## 🔌 Documentação da API (Endpoints)

A API roda por padrão em `http://localhost:8080`. Abaixo estão os exemplos para teste no **Postman**.

### 1. Listar Mesas Livres
Verifica a disponibilidade do salão e mostra qual garçom atende cada mesa.

* **Método:** `GET`
* **URL:** `/mesas`

### 2. Abrir Conta (Ocupar Mesa)
Inicia o atendimento de um cliente em uma mesa específica.

* **Método:** `POST`
* **URL:** `/contas`
* **Body (JSON):**
    ```json
    {
        "idMesa": 1,
        "nomeCliente": "Lucas Porto"
    }
    ```
* *Nota: Se a mesa já estiver ocupada, retornará status `409 Conflict`.*

### 3. Realizar Pedido
Adiciona itens à conta aberta.

* **Método:** `POST`
* **URL:** `/pedidos`
* **Body (JSON):**
    ```json
    {
        "idConta": 1,
        "itens": [
            {
                "nomePrato": "Hamburguer",
                "quantidade": 2
            },
            {
                "nomePrato": "Coca Cola",
                "quantidade": 1
            }
        ]
    }
    ```

### 4. Fechar Conta (Pagamento)
Finaliza a conta, libera a mesa para o próximo cliente e gera o recibo detalhado.

* **Método:** `POST`
* **URL:** `/pagamentos`
* **Body (JSON):**
    ```json
    {
        "idConta": 1
    }
    ```
* **Retorno (Recibo):**
    ```json
    {
        "idConta": 1,
        "nomeCliente": "Lucas Porto",
        "nomeMesa": "Mesa 1",
        "itensConsumidos": [
            {
                "nomePrato": "Hamburguer",
                "quantidade": 2,
                "precoUnitario": 25.0,
                "subtotal": 50.0
            },
            ...
        ],
        "valorTotal": 55.0,
        "dataPagamento": "07/12/2025 21:30:00"
    }
    ```

---

## 🛡️ Tratamento de Exceções

O projeto conta com um **Global Exception Handler** que padroniza as respostas de erro:

* **404 Not Found:** Quando Mesa, Conta ou Produto não são encontrados.
* **409 Conflict:** Violação de regras de negócio (Ex: Tentar pagar uma conta já paga ou pedir item em conta fechada).
* **500 Internal Server Error:** Erros genéricos de sistema.

---

## 🏗️ Estrutura do Projeto

```text
com.devbackend.restaurante
│
├── config          # Configurações Globais (Exception Handler)
├── controller      # Camada REST (Entrada de dados)
├── dto             # Objetos de Transferência de Dados (Inputs/Outputs)
├── model           # Entidades JPA (Espelho do Banco de Dados)
├── repository      # Interfaces de Acesso a Dados (Spring Data JPA)
├── service         # Regras de Negócio e Validações
└── CargaInicial    # Script para popular o banco automaticamente