# DB Connection Manager

## ⚠️ Atenção/Attention
Nunca faça o upload de arquivos .properties com suas credenciais de banco de dados para o repositório. Eu adicionei apenas para ilustrar.
Never, ever upload .properties file with your database credentials to repository. I added it just to illustrate.


## English

This is a Maven project designed to manage connections to relational databases. It provides a simple class with two methods: 
 - `getConnection()` to establish the connection to the database
 - `close()` to close resources such as:
   - `ResultSet`
   - `PreparedStatement`
   - `Connection`

### Configuration

Database credentials (such as URL, username, and password) must be configured in a `.properties` file, located inside `src/main/resources`. This file will be used by the project to establish the connection to the database.

### Example of `db.properties` file:

```properties
DB_URL  = jdbc:mysql://localhost:port/yourdatabase
DB_USER = yourusername
DB.PASS = yourpassword
```

***

## Português

Este é um projeto Maven destinado a gerenciar a conexão com bancos de dados relacionais. Ele oferece uma classe simples com dois métodos: 
 - `getConnection()` to establish the connection to the database
 - `close()` to close resources such as:
   - `ResultSet`
   - `PreparedStatement`
   - `Connection`

## Configuração

As credenciais do banco de dados (como URL, usuário e senha) devem ser configuradas em um arquivo `.properties`, localizado dentro de `src/main/resources`. Esse arquivo será utilizado pelo projeto para se conectar ao banco de dados.

### Exemplo de arquivo `db.properties`:

```properties
DB_URL  = jdbc:mysql://localhost:port/yourdatabase
DB_USER = yourusername
DB.PASS = yourpassword
```
