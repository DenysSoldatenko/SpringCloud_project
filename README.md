# SpringCloud_project: Build a Blog & Quiz Platform
This project uses Spring Cloud to create a dynamic platform for blog posts and quizzes.

### **Key Features:**
- **Modular Design:** Composed of independent modules for blog, quizzes, common functionality, API gateway, and configuration management.
- **Spring Cloud Integration:** Leverages Eureka for service discovery and GitHub for centralized configuration.
- **Data Generator:** Provides a tool for populating your platform with test data.

### **Module Breakdown:**
- **app-blog:** Houses the blog functionality, including creating, managing, and commenting on posts.
- **app-common:** Contains shared components used across different modules.
- **app-gateway:** Acts as the central entry point, routing requests to the appropriate microservices.
- **app-quiz:** Implements quiz creation, taking, and evaluation features.
- **data-generator:** Generates mock data for testing purposes.
- **eureka-server:** Enables service discovery for microservices to interact with each other.
- **github-config-server:** Stores and manages configuration files hosted on GitHub.