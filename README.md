## OpenLib Sistema para Gestion de Biblioteca

Este es el repositorio de nuestro proyecto, Diseñamos e implementamos una arquitectura orientada a microservicios para manejar los requerimientos principales de una biblioteca.
El proyecto está estructurado como un monorepositorio, lo que nos permite tener los servicios ordenados en un solo lugar pero el uso es de forma totalmente desacoplada.

## integrantes

## Diego Arriagada Aránguiz Encargado de la programacion de los microservicios: UsuarioMS, PrestamoMS, MultaMS y ReseñaMS.
## Maximiliano Rosales Encargado dede la programacion de los microservicios: CatalogoMS, IventarioMS, NotificacionesMS y ReportesMS.

## Arquitectura del Sistema y Tecnologías
Usamos Java 25 con Spring Boot 4.0.6 / Maven.
Base de Datos Relacional Oracle SQL (utilizando esquemas e instancias separadas para garantizar que cada microservicio maneje sus propios datos de forma exclusiva).
Arquitectura: CSR = Controller - Service - Repository.
Validación de Datos: Uso de Bean Validation (JSR 380) para el control estricto de entradas desde las peticiones HTTP.

## Puertos Asignados
Para evitar conflictos al ejecutar el proyecto de forma local, cada microservicio se configuró en un puerto específico:

UsuarioMS: http://localhost:8080 Control de Usuarios/Lectores.
PrestamoMS: http://localhost:8083 Control y Registro de prestamos de Libros.
MultaMS: http://localhost:8082 Sistema de Sanciones por No devolver un Libro o Devolverlo en mal estado.
ReseñaMS: http://localhost:8081 Sistema independiente para la Valoracion con notas del 1 al 7 y comentario de libros.
InventarioMS: http://localhost:8085
CatalogoMS: http://localhost:8084
ReportesMS: http://localhost:8087
NotificacionesMS: http://localhost:8086 

## Manejo de Errores y Logs
1. Para eel manejo de Errores en el sistema implementamos un archivo centralizado (GlobalExceptionHandler). Ya que lo que hace es agarrar los errores cuando alguien escribe mal un dato en el formulario (por ejemplo, un RUT mal puesto o un correo sin el @) y devolver un mensaje limpio en JSON que diga exactamente qué falló  (como el 400 Bad Request).

2. Implementamos SLF4J en el código, ya que esto nos sirve para que, mientras programamos, la consola del servidor nos vaya mostrando textos ordenados con `log.info()` cuando un proceso sale bien (como "Usuario creado con éxito") o con `log.warn()` si algo salio mal, lo que nos ayuda con el control de errores.


## URL GitHub
https://github.com/diarriagadaa-hue/OpenLib