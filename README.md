# SpringCloud_project: Build a Post & Quiz Platform

This project uses Spring Cloud to create a dynamic platform for managing blog posts and quizzes.

## Key Features:
- **Modular Architecture:** Independent modules for posts and quizzes management.
- **Service Discovery:** Uses Eureka for seamless microservice interaction.
- **Data Generation:** Tools for populating test data within both post and quiz modules.
- **Security:** JWT authentication integrated across services.
- **Elasticsearch Integration:** Enables fast search capabilities within the quiz module.

## Module Overview:
- **post-service:** Blog post creation, management, and commenting functionalities.
- **quiz-service:** Quiz creation, attempt management, and evaluation.
- **gateway-service:** API gateway for routing requests to the appropriate microservices.
- **eureka-server:** Handles service discovery.

## Swagger URLs:
- **Post Service:** [http://localhost:8081/swagger-ui/index.html](http://localhost:8081/swagger-ui/index.html)
- **Quiz Service:** [http://localhost:8085/swagger-ui/index.html](http://localhost:8085/swagger-ui/index.html)
- **Gateway Service:** [http://localhost:8765/webjars/swagger-ui/index.html](http://localhost:8765/webjars/swagger-ui/index.html)

---

> **Note:** Ensure all services are running before accessing the Swagger documentation links.