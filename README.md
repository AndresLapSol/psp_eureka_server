# 📡 PSP Eureka Service Registry

A Spring Boot application acting as a **Netflix Eureka** Service Registry for microservices discovery. 🚀

---

## 🎯 Objectives
- Provide a centralized service registry 🗂️  
- Allow microservices to register and discover each other 🔎  
- Enable high availability and load balancing  
- Offer a web dashboard for monitoring registered instances 📊

---

## ✨ Features
- 🏷️ **Spring Cloud Netflix Eureka** server out-of-the-box  
- 🔄 **Self-preservation mode** enabled by default  
- 📈 **Dashboard UI** at `/` for real-time instance status  
- ⚙️ **Customizable properties** via `application.yml` or `application.properties`

---

## 🚀 Technologies & Tools
- **Java 17+**  
- **Spring Boot 3.x**  
- **Spring Cloud Netflix Eureka 4.x**  
- **Maven**  
- **Actuator** for health checks  

---

## 🔧 Prerequisites
1. JDK 17 or higher installed  
2. Maven 3.6+ installed  
3. (Optional) Docker for containerization  

---

## 🛠️ Installation & Setup

### 1. Clone the repository
```bash
git clone https://github.com/AndresLapSol/psp_eureka_server.git
cd psp_eureka_server
```

### 2. Configure application properties
- Edit `src/main/resources/application.yml` (or `application.properties`) to set ports and security:
```yaml
server:
  port: 8761

eureka:
  server:
    enable-self-preservation: true
  client:
    register-with-eureka: false
    fetch-registry: false
```

### 3. Build the project
```bash
mvn clean package
```

### 4. Run the Eureka Server
```bash
mvn spring-boot:run
```
- Access the dashboard at [http://localhost:8761](http://localhost:8761) 🌐

---

## 📂 Project Structure
```
psp_eureka_server/
├── src/
│   ├── main/
│   │   ├── java/com/andreslapsol/
│   │   │   └── eurekaserver/
│   │   │       ├── EurekaServerApplication.java  # @EnableEurekaServer entry point
│   │   │       └── config/                       # Custom configurations (if any)
│   │   └── resources/
│   │       ├── application.yml                   # Eureka server settings
│   │       └── bootstrap.yml (optional)          # Bootstrap configs
│   └── test/
│       └── java/com/andreslapsol/               # Unit and integration tests
├── pom.xml
└── LICENSE
```

---

## 🤝 Contributing
Contributions are welcome!  
1. Fork the repo  
2. Create a branch: `git checkout -b feature/YourFeature`  
3. Commit your changes: `git commit -m "Add exciting feature"`  
4. Push and open a Pull Request ✨

---

## ⚖️ License
This project is licensed under the **MIT License**. See the `LICENSE` file for details.

---

## 📫 Contact
Developed by **AndresLapSol**  
GitHub: [AndresLapSol](https://github.com/AndresLapSol)  
Email: `andreslapsol@gmail.com`
