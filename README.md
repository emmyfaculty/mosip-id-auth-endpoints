# MOSIP id-authentication - Health & Audit Endpoints (Sample Implementation)

This small project provides two endpoints to integrate into MOSIP's id-authentication module:

- `GET /api/v1/health/details` - returns health and metadata (status, timestamp, serviceName, version, environment, configuredProperty)
- `POST /api/v1/audit/log` - accepts audit events and stores them in an H2 in-memory database (eventType and userId are mandatory)

## Build & Run
1. Build: `mvn clean package`
2. Run: `mvn spring-boot:run`
3. H2 console: `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:mosipdb`)
4. Swagger UI (if springdoc is present): `http://localhost:8080/swagger-ui.html`

## Example Requests
Health:
```bash
curl -v http://localhost:8080/api/v1/health/details
```

Audit:
```bash
     curl -X POST http://localhost:8080/api/v1/audit/log -H 'Content-Type: application/json' \
     -d '{"eventType":"LOGIN","description":"User attempted login","userId":"12345"}'
```

## Swagger / OpenAPI Documentation

Once the service is running, you can explore the APIs via Swagger UI:

- Swagger UI: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
- OpenAPI JSON: [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

### Available Endpoints
- `GET /api/v1/health/details` → Service health details
- `POST /api/v1/audit/log` → Log an audit/event
