#CRUD Empleado

Este es un proyecto back-end que combina el framework Spring Boot con el lenguaje de programación Java. La aplicación utiliza una base de datos MySQL.

## Descripción del Proyecto

La aplicación CRUD de Empleados tiene la capacidad para administrar los datos de los empleados. Nos permite mostrar una lista de todos los empleados y los usuarios pueden realizar operaciones como crear nuevos empleados, actualizar la información de los empleados existentes y eliminar empleados.

## Tecnologias Usadas

### Back-end
- JDK 11
- Spring Boot
- Maven
- Embedded Tomcat
- MySQL
- Postman
- Log4j2
---

### Endpoints

|HTTP Method|URL|Description|
|---|---|---|
|`GET`|http://localhost:8000/swagger/ui | Swagger UI page |

#### Empleado Service

|HTTP Method|URL|Description|
|---|---|---|
|`POST`|http://localhost:8080/api/empleados | Crear uno o mas Empleados |
|`PUT`|http://localhost:8080/api/empleados/{empleadoId} | Actualizar Empleado por ID |
|`GET`|http://localhost:8080/api/empleados | Listar Empleados |
|`DELETE`|http://localhost:8080/api/empleados/{empleadoId} | Eliminar Empleado por ID |


## Empezando

Para correr localmente el proyecto, sigue estos pasos:

1. Clona el repositorio: `git clone https://github.com/daniel070893/crudempleados/`
2. Levanta tu base de datos MySQL de manera local.
2. Configura las dependencias del back-end y ejecuta la aplicación de Spring Boot.
3. Consume la aplicación a traves de Postman con los diferentes Endpoints mencionados anteriormente.

Para mas detalles de las instrucciones, por favor acercate a la documentación del proyecto en el repositorio.

## Repositorio

El repositorio del proyecto se puede encontrar en GitHub: [https://github.com/daniel070893/crudempleados/]
