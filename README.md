## 🎬 MovieApp – Sistema de Gerenciamento de Filmes e Análises

MovieApp é uma aplicação web desenvolvida em Spring Boot, que permite cadastrar, visualizar, editar e excluir filmes, além de adicionar análises (reviews) para cada filme.
O objetivo do projeto é aplicar conhecimentos em Java, Spring Boot, REST API e Front-end básico, demonstrando habilidades práticas em desenvolvimento de aplicações web.

## 🚀 Tecnologias utilizadas

- Java 17+
- Spring Boot
- Spring Data JPA
- MySQL
- HTML5 / CSS3
- JavaScript
- jQuery
- Maven
- Git & GitHub

## ✅ Funcionalidades

### 🎥 Filmes
- Cadastrar um novo filme  
- Listar todos os filmes  
- Visualizar detalhes de um filme  
- Editar filme  
- Excluir filme  

### 📝 Análises
- Cadastrar análises para um filme  
- Listar análises  
- Editar análises  
- Excluir análises  

### 🌗 Interface
- Alternância entre modo claro e modo escuro  
- Layout responsivo e organizado  
- Tabela estilizada (visual moderno)  

### 🧪 Testes
- Testes unitários básicos implementados  
- Estrutura de testes configurada com JUnit

## ▶️ Como executar o projeto

- Clone o repositório: 
   git clone https://github.com/seu-usuario/movieapp.git

- Configure o arquivo application.properties com seu banco/MySQL.

- Execute mvn spring-boot:run

- Acesse: http://localhost:8080

## 🌐 Interface Web

Acesse via navegador para usar todas as funcionalidades de filmes e análises:

- Listar, adicionar, editar e excluir filmes

- Listar, adicionar, editar e excluir análises

- Alternância entre modo claro e escuro

- Layout responsivo

 ----

## 🛠 API REST (Endpoints JSON)

Todos os endpoints retornam ou recebem dados em formato JSON.
Para testar, você pode usar ferramentas como Postman ou Insomnia.

<details> <summary>🎥 Filmes</summary>
### Listar todos os filmes

- **GET** `/api/filmes`
- **Request Body**
```json
[
  {
    "id": 1,
    "titulo": "Matrix",
    "sinopse": "Um hacker descobre a verdade sobre a realidade.",
    "genero": "Sci-Fi",
    "anoLancamento": 1999
  }
]
````
- Response: retorna uma lista de filmes.
```
```
### Buscar filme por ID

- **GET** `/api/filmes/{id}`
- **Request Body**
```json
[
  {
   "id": 1,
   "titulo": "Matrix",
   "sinopse": "Um hacker descobre a verdade sobre a realidade.",
   "genero": "Sci-Fi",
   "anoLancamento": 1999
  }
]
```
- Response: Retorna o filme correspondente ao ID.
```
```
### Adicionar novo filme
- **POST** `/api/filmes`
- **Request Body**
```json
[
  {
  "titulo": "Inception",
  "sinopse": "Um ladrão invade sonhos para roubar segredos.",
  "genero": "Sci-Fi/Thriller",
  "anoLancamento": 2010
  }
]
```
- Response (201 Created): Retorna o filme criado incluindo o campo id.
```
```
### Atualizar filme

- **PUT** `/api/filmes/{id}`
- **Request Body**
```json
[
  {
  "titulo": "Matrix Reloaded",
  "sinopse": "Continuação da saga Matrix.",
  "genero": "Sci-Fi",
  "anoLancamento": 2003
  }
]
```
- Response (200 OK): Retorna o filme atualizado.
```json
[
  {
  "id": 1,
  "titulo": "Matrix Reloaded",
  "sinopse": "Continuação da saga Matrix.",
  "genero": "ficção",
  "anoLancamento": 2004
  }
]
```
### Excluir filme

- **DELETE** `/api/filmes/{id}`

- Response( 204 No Content): exclui o recurso sem corpo de retorno.

</details> <details> <summary>📝 Análises</summary>
  
#### Lista de análises

- **GET** `/api/analises`
```json
[
  {
    "id": 1,
    "filmeId": 1,
    "comentario": "Excelente filme de ficção científica!",
    "nota": 9
  }
]
````
- Response: Retorna lista de análises cadastradas.
```
```
### Buscar análise por ID

- **GET** `/api/analises/{id}`
- **Request Body**
```json
[
  {
  "id": 1,
  "filmeId": 1,
  "comentario": "Excelente filme de ficção científica!",
  "nota": 9
  }
]
```
- Response: Retorna a análise correspondente.
  Se não existir → 404 Not Found.
```
```
### Adicionar nova análise

- **POST** `/api/analises`
- **Request Body**
```json
[
  {
  "filmeId": 3,
  "comentario": "Muito criativo e envolvente.",
  "nota": 8
  }
]
```
- Response (201 Created): Retorna a análise criada com campo id.
```json
```
### Atualizar análise

- **PUT** `/api/analises/{id}`
```json
[
  {
  "filmeId": 3,
  "comentario": "Excelente roteiro.",
  "nota": 8
  }
]
```
- Response (200 OK): Retorna a análise atualizada.
```json
[
  {
  "id": 3,
  "filmeId": 3,
  "comentario": "Atualizado: Excelente roteiro e efeitos visuais.",
  "nota": 9
  }
]
```
### Excluir análise

- **DELETE** `/api/analises/{id}`

- Response (204 No Content): Análise removida com sucesso.

</details> ```

