# Ibovespa Application

## Descrição
A aplicação **Ibovespa** é uma API REST desenvolvida em **Java** com **Spring Boot 3**, **JPA**, **Lombok** e **H2 Database**. O objetivo principal é classificar operações financeiras com base em três categorias de risco predefinidas: **EXPIRED**, **HIGHRISK** e **MEDIUMRISK**. A API permite a classificação automática dessas operações com base na data de referência informada.

## Tecnologias Utilizadas
- **Java 17**
- **Spring Boot 3**
- **Spring Data JPA**
- **H2 Database** (Banco de dados em memória)
- **Lombok**
- **JUnit 5** (Testes unitários)

## Estrutura do Projeto
```
ibovespa/
│── src/
│   ├── main/
│   │   ├── java/com/example/ibovespa/
│   │   │   ├── IbovespaApplication.java
│   │   │   ├── controller/
│   │   │   │   ├── TradeController.java
│   │   │   ├── model/
│   │   │   │   ├── Trade.java
│   │   │   ├── repository/
│   │   │   │   ├── TradeRepository.java
│   │   │   ├── service/
│   │   │   │   ├── TradeService.java
│   │   │   ├── enums/
│   │   │   │   ├── RiskCategory.java
│   │   │   ├── test/
│   │   │   │   ├── TradeServiceTest.java
│   ├── resources/
│   │   ├── application.properties
│   │   ├── data.sql
```

## Configuração e Execução
### Requisitos
- **Java 17+**
- **Maven**

### Como Executar
1. Clone o repositório:
   ```sh
   git clone https://github.com/seu-usuario/ibovespa.git
   ```
2. Acesse o diretório do projeto:
   ```sh
   cd ibovespa
   ```
3. Compile e execute a aplicação:
   ```sh
   mvn spring-boot:run
   ```
4. A API estará disponível em:
   ```sh
   http://localhost:8080
   ```

## Endpoints
### 1. Classificar Todas as Operações
**GET** `/trades?referenceDate=yyyy-MM-dd`

#### Parâmetro
- `referenceDate` (String): Data de referência para classificação (formato `yyyy-MM-dd`).

#### Exemplo de Requisição
```sh
curl -X GET "http://localhost:8080/trades?referenceDate=2020-12-11"
```

#### Exemplo de Resposta
```json
[
  "HIGHRISK",
  "EXPIRED",
  "MEDIUMRISK",
  "MEDIUMRISK"
]
```

## Banco de Dados
A API usa um banco de dados em memória **H2** e inicializa automaticamente 10 registros através do arquivo `data.sql`. Para acessar a interface do banco:

1. Acesse o console H2 em:
   ```sh
   http://localhost:8080/h2-console
   ```
2. Configure:
   - **JDBC URL:** `jdbc:h2:mem:testdb`
   - **User:** `sa`
   - **Password:** (vazio)

## Testes
A aplicação possui testes unitários com **JUnit 5**. Para executá-los:
```sh
mvn test
```

## Autor
[Ricardo Ferreira Martins]

---

