# Project Book API

![Java](https://img.shields.io/badge/Java-17-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen)
![MySQL](https://img.shields.io/badge/MySQL-8.0-orange)
![Build](https://img.shields.io/badge/build-passing-brightgreen)
![Coverage](https://img.shields.io/badge/coverage-0%25-green)

API REST para gestionar libros, creada con Java y Spring Boot. Permite crear, listar, actualizar y eliminar libros, con base de datos MySQL y manejo de errores consistente.

---

## Tecnologías

- Java 17
- Spring Boot 3.x
- MySQL 8.0
- Maven
- Spring Data JPA
- Spring Web
- SLF4J / Logback
- JUnit 5 / Mockito
- Docker / Docker Compose
- Swagger / OpenAPI (para documentación de API)

---

## Requisitos

- JDK 17 o superior
- Maven 3.x
- Docker y Docker Compose (opcional para levantar MySQL)

---

## Estructura del proyecto

---
### Levantar MySQL con Docker Compose

El proyecto usa un archivo `.env` para configurar las credenciales de MySQL:

```dotenv
MYSQL_DATABASE=hexagonal_db
MYSQL_USER=hex_user
MYSQL_PASSWORD=hex_pass
MYSQL_ROOT_PASSWORD=root
```


## Instalación y configuración
Levantar MySQL con Docker Compose
- Ddocker-compose up -d
- Ejecuta automáticamente los scripts:
- 01_create_tables.sql → creación de tablas (authors, categories, books)
- 02_insert_data.sql → inserción de datos iniciales

## Construir y ejecutar la aplicación
> mvn clean install
>
> mvn spring-boot:run


### 1. Clonar el repositorio

```bash
git clone https://github.com/usuario/aravena-book-api.git
cd aravena-book-api