# dev_back_end
Repositório para desenvolvimento do projeto da disciplina de Back End da Resicência de TI.

**Disciplina:** Desenvolvedor Back-End

- Linguagem: Java
- Framework: Spring Boot
---

### 👥 Alunos
| Nome | Matrícula |
|-|-|
| **Carlos Henrique Silva Bispo Rodrigues** | 2025200222 |
| **Lucas Rodrigues Porto** | 2025200247 |
---

O trabalho envolve a implementação de uma API para um sistema de Garçom Eletrônico.

### Diagrama de Classes

<img width="1312" height="885" alt="image" src="https://github.com/user-attachments/assets/c693d1f8-ddc9-47d0-b2ed-8b882dc873e0" />

### ENDPOINTS CRIADOS

#### GET /mesas

- Objetivo: Visualizar disponibilidade de mesas
- Funcionamento: Sistema consulta a base de dados filtrando as mesas e seus status
- Retorno: Lista contendo dados das mesas

  
#### POST /contas

- Objetivo: Ocupar uma mesa e iniciar o atendimento
- Funcionamento: Recebe o id da mesa e nome do cleinte, caso esteja disponível é realizada a vinculação e alterado status da mesa
- Retorno: Dados da mesa em questão

  
#### POST /pedidos

- Objetivo: Registrar o consumo
- Funcionamento: Recebe os itens e id da conta para realizar o vínculo
- Retorno: Retorno da conta

  
#### POST /pagamentos

- Objetivo: Finalizar o ciclo e liberar a mesa
- Funcionamento: Localiza a conta, registra o pagamento e altera a disponibilidade da mesa
- Retorno: Mensagem de sucesso
