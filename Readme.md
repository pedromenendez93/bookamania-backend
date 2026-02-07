# Base Archetype for Java 25 and Spring Boot Applications

This repository contains a base archetype for developing future applications using Java 25 and Spring Boot. The purpose of this project is to provide a solid structure and an initial set of configurations that can serve as a starting point for the development of various applications.

## Features

- **Java 25:** We use the latest version of Java to leverage the language's newest features and improvements.

- **Spring Boot:** The project is configured with Spring Boot, making it easier to create robust and scalable applications.

- **Predefined Configuration:** We've included predefined configurations for aspects such as dependency management, database setup, basic security, and logging configuration. This allows you to focus on developing your features without worrying about initial setup.

- **Organized Structure:** The project structure follows Spring Boot's recommended best practices, helping you keep your code organized and easy to maintain.

- **Basic CRUD Implementation:** We've added a basic CRUD (Create, Read, Update, Delete) implementation for managing `Product` entities. This includes endpoints for creating, reading, updating, and deleting products.

- **Spring Security Integration:** Basic authentication is configured using Spring Security with in-memory user management for secured endpoints.

## Prerequisites

Make sure you have the following installed on your system:

- **Java 25:** You can download it from [here](https://www.oracle.com/java/technologies/javase-downloads.html) or use an OpenJDK distribution like [Eclipse Temurin](https://adoptium.net/).

- **Maven:** We use Maven as the dependency management and build tool. You can download it from [here](https://maven.apache.org/download.cgi).

## How to Use This Archetype

1. **Clone the Repository:** Start by cloning this repository to your local machine.

    ```bash
    git clone https://github.com/pedromenendez93/springboot-java-archetype.git
    ```

2. **Customize the Project:** You can modify the structure and configurations according to the specific needs of your application.

3. **Development:** Add your own classes, controllers, services, and components following Spring Boot conventions.

4. **Testing:** Utilize Spring Boot's testing capabilities to ensure your code's quality.

5. **Compilation:** Use Maven to compile your project.

    ```bash
    mvn clean install
    ```

6. **Execution:** Run your application using Maven's Spring Boot plugin.

    ```bash
    mvn spring-boot:run
    ```

## Available Endpoints

### Public Endpoints (No Authentication Required)

- **Greeting:** `GET /api/public/greeting` - Returns a greeting message
- **Create a Product:** `POST /api/product`
- **Get All Products:** `GET /api/product`
- **Get a Product by ID:** `GET /api/product/{id}`
- **Update a Product:** `PUT /api/product/{id}`
- **Delete a Product:** `DELETE /api/product/{id}`

### Secured Endpoints (Authentication Required)

- **Secured Greeting:** `GET /api/secured/greeting` - Returns a secured message (requires HTTP Basic Authentication)

### Authentication

The secured endpoints require HTTP Basic Authentication with the following default credentials:

- **Username:** `admin`
- **Password:** `password`

You can test secured endpoints using tools like curl:

```bash
curl -u admin:password http://localhost:8080/api/secured/greeting
```