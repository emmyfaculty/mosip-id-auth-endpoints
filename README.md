# MOSIP id-authentication - Health & Audit Endpoints (Sample Implementation)

This project provides a sample implementation of **Health** and **Audit** endpoints that can integrate into MOSIP's ID authentication module.  

- `GET /api/v1/health/details` → Returns service health and metadata (status, timestamp, serviceName, version, environment, configuredProperty)  
- `POST /api/v1/audit/log` → Accepts audit events and stores them in an H2 in-memory database (`eventType` and `userId` are mandatory)  

---

## ⚙️ Prerequisites
- **Java 17**  
- **Maven 3.6+**  
- Git  

---

## 🛠️ Setup & Build

1. **Clone the repository**
   ```bash
   git clone https://github.com/<your-org>/mosip-id-auth-endpoints.git
   cd mosip-id-auth-endpoints
   ```

2. **Build the project**
   ```bash
   mvn clean package
   ```

3. **Run the service**
   ```bash
   mvn spring-boot:run
   ```
   Or run the JAR:
   ```bash
   java -jar target/id-auth-endpoints-sample-1.0.0.jar
   ```

---

## ▶️ Usage

- **H2 Database Console**  
  [http://localhost:8080/h2-console](http://localhost:8080/h2-console)  
  - JDBC URL: `jdbc:h2:mem:mosipdb`  
  - User: `sa`  
  - Password: *(empty)*  

- **Swagger UI** (if Springdoc is present)  
  [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)  
  [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)  

- **OpenAPI JSON**  
  [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)  

---

## 📌 Example Requests

### Health Check
```bash
curl -v http://localhost:8080/api/v1/health/details
```

### Audit Log
```bash
curl -X POST http://localhost:8080/api/v1/audit/log \
  -H 'Content-Type: application/json' \
  -d '{"eventType":"LOGIN","description":"User attempted login","userId":"12345"}'
```

---

## 📑 API Endpoints

- `GET /api/v1/health/details` → Service health details  
- `POST /api/v1/audit/log` → Log an audit/event  

---

## 🧪 Running Tests
```bash
mvn test
```

---

## 🤝 Contributing
1. Fork the repo  
2. Create a feature branch:  
   ```bash
   git checkout -b feature/my-feature
   ```
3. Commit your changes:  
   ```bash
   git commit -m "Add my feature"
   ```
4. Push the branch:  
   ```bash
   git push origin feature/my-feature
   ```
5. Open a Pull Request  

---

## 📄 License
This project is licensed under the MIT License.  
