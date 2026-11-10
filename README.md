# API Calendario

Spring Boot + PostgreSQL con arquitectura Onion (Maven multimódulo). Arquitectura de Software II.

## Módulos
| Módulo | Contenido |
|---|---|
| dominio | Entidades `Tipo` y `Calendario` |
| core | Interfaces de servicios, repositorios e integración |
| aplicacion | `TipoServicio`, `CalendarioServicio` |
| infraestructura | Entidades JPA, repositorios, mapeadores y cliente de la API Festivos |
| presentacion | `Aplicacion`, controladores y `application.properties` |

## Base de datos
Crear la base `calendario` en PostgreSQL y ejecutar `basedatos/script.sql` (crea las tablas y los tipos 1 = Dia laboral, 2 = Fin de Semana, 3 = Dia festivo).
Ajustar usuario y contraseña en `presentacion/src/main/resources/application.properties`.

## Ejecutar
Primero debe estar corriendo la API Festivos (puerto 8080). Esta API corre en el puerto 8081.

    mvn clean install
    mvn spring-boot:run -pl presentacion

La URL de Festivos se cambia en `servicio.festivos.url`.

## Endpoints
- `GET /api/calendario/generar/{anio}` genera y guarda el año; responde `true`.
- `GET /api/calendarios/listar/{anio}` lista el calendario del año: `[{id, fecha, tipo:{id,tipo}, descripcion}]`.
- CRUD de tipos: `/api/tipos/listar`, `/obtener/{id}`, `/buscar/{tipo}`, `POST /agregar`, `PUT /modificar`, `DELETE /eliminar/{id}`.

Reglas: festivo (según API Festivos) > fin de semana (sábado/domingo) > día laboral.
