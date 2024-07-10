# Challenge ForoHub

Este es un desafío en el marco del programa ONE dictado por Alura Latam y Oracle Next Education

ForoHub es una API diseñada para crear y gestionar tópicos, permitiendo a los usuarios crear, actualizar, listar y eliminar tópicos que se almacenan en una base de datos.

## Funcionalidades

1. Crear Tópico: Permite crear un nuevo tópico y guardarlo en la base de datos.
2. Listar Tópicos: Muestra todos los tópicos registrados en la base de datos.
3. Obtener Tópico por ID: Permite buscar un tópico en la base de datos utilizando su ID.
4. Actualizar Tópico: Actualiza los detalles de un tópico existente.
5. Eliminar Tópico: Elimina (lógicamente) un tópico de la base de datos.

## Requisitos previos

- Java JDK 8 o superior.
- Maven 3.x.x.
- Base de datos MySQL configurada.

## Instrucciones de instalación

1. Clona este repositorio en tu máquina local.
```
https://github.com/egrigolatto/ForoHub.git
```
2. Importa el proyecto en tu IDE favorito como un proyecto Maven.
3. Configura tu base de datos en 'application.properties'
```commandline
spring.datasource.url=jdbc:mysql://localhost:3306/forohub
spring.datasource.username=root
spring.datasource.password=tu_contraseña
api.security.secret=tu_clave
```
4. Ejecuta la aplicación desde 'ForoHubApplication.java'.

## Uso

Al iniciar la aplicación, puedes utilizar herramientas como Postman o Insomnia para interactuar con la API.

**Las rutas disponibles son:**

- Crear Tópico (POST): /topicos
- Listar Tópicos (GET): /topicos
- Obtener Tópico por ID (GET): /topicos/{id}
- Actualizar Tópico (PUT): /topicos/{id}
- Eliminar Tópico (DELETE): /topicos/{id}

**Ejemplos de URLs para búsqueda avanzada:**

- Listar tópicos de un año específico: http://localhost:8080/topicos?year=2024
- Listar tópicos por curso: http://localhost:8080/topicos?curso=PYTHON
- Listar tópicos por curso y año específico: http://localhost:8080/topicos?curso=JAVASCRIPT&year=2024

**Ejemplo de creación de un tópico:**

- Request (POST /topicos)
```
{
    "autor": "15",
    "mensaje": "Consulta Java",
    "curso": "JAVA",
    "titulo": "consulta Java"
}
```
- Response
```
{
    "id": 10,
    "titulo": "consulta Java",
    "mensaje": "Consulta Java",
    "curso": "JAVA",
    "fechaCreacion": "2024-07-10T13:39:15.751619",
    "status": true,
    "autor": "15"
}
```

## Dependencias utilizadas
- spring-boot-starter-web
- spring-boot-devtools
- lombok
- spring-boot-starter-data-jpa
- flyway-core
- flyway-mysql
- mysql-connector-j
- spring-boot-starter-validation
- spring-boot-starter-security
- java-jwt
- springdoc-openapi-starter-webmvc-ui

## Tecnologías utilizadas
- Java
- Spring Boot
- Hibernate
- Maven
- MySQL


- [Visita la página de Alura](https://app.aluracursos.com/dashboard)
- [ONE - Oracle Next Education](https://www.oracle.com/ar/education/oracle-next-education/)