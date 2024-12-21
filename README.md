# UtilDB - Java Database Utility

## Description

The `UtilDB` project provides utility methods for managing database connections and resources in Java. It includes:

- A method to open a database connection using JDBC.
- A method to close multiple resources like `Connection`, `PreparedStatement`, and `ResultSet` with automatic handling of exceptions.

This project is useful for Java applications that interact with databases, providing a clean and reusable approach to managing database connections and resources.

## Features

- **Database Connection**: Open a database connection using properties loaded from `application.properties`.
- **Resource Management**: Automatically close database resources (`Connection`, `PreparedStatement`, `ResultSet`) using the `close()` method.
- **Error Handling**: Proper error handling during connection opening and resource closing.

## Prerequisites

- Java 8 or higher.
- JDBC-compatible database (MySQL, PostgreSQL, etc.).
- `application.properties` configuration file for database credentials.

## Usage
Establishing connection:
```
[...code]
Connection conn = UtilDB.open();
[...code]
```

Free resources:
```
Connection conn;
PreparedStatement ps;
ResultSet rs;

[...code]
finally {
  UtilDB.close(ps, ps, conn);
}
[...code]
```
