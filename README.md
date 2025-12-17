# Candidate Service

Servicio REST para gestionar clientes: registro, listado y métricas. Incluye seguridad JWT, persistencia MySQL con Flyway, documentación Swagger, observabilidad con Actuator/Prometheus, y despliegue con Docker.

## Requisitos
- Java 17
- Maven 3.9+
- Docker y Docker Compose

## Configuración
- Variables:
  - `JWT_SECRET`: secreto para firmar tokens
  - `MYSQL_USER`, `MYSQL_PASSWORD`, `MYSQL_DB`, `MYSQL_HOST`, `MYSQL_PORT`

## Ejecución local
1. `docker compose up -d`
2. `mvn -DskipTests package`
3. `java -jar target/candidate-service-0.0.1-SNAPSHOT.jar`

## Seguridad
- Obtener token: `POST /api/v1/auth/token` con JSON `{ "username": "admin", "password": "password" }`
- Usar `Authorization: Bearer <token>` en llamadas protegidas.

## Endpoints
- `POST /api/v1/clients` crea cliente.
- `GET /api/v1/clients` lista clientes con fecha estimada.
- `GET /api/v1/clients/metrics` métricas de edad.
- Swagger: `/swagger-ui.html`
- Actuator: `/actuator/health`, `/actuator/prometheus`

## Pruebas
`mvn test`

## Despliegue
Se incluye `Dockerfile` y `docker-compose.yml`. Adaptar variables para entorno cloud y base de datos administrada (RDS/MySQL).

