# Fake Twitter Project

This project is a simple API that mimics basic Twitter functionalities. It was created as part of my efforts to improve and deepen my knowledge, particularly in the areas of OAuth2 and Spring Security.

## Features

- **User Management**: Create and list users.
- **Tweet Feed**: Create, delete, and list tweets.
- **Security**: Implemented OAuth2 and Spring Security for authentication and authorization.
- **Database**: Used MySQL for data storage.
- **Token Management**: JWT (JSON Web Token) was used for secure token handling.
- **Password Security**: Integrated Bcrypt for password hashing.

## Technologies Used

- **Java**
- **Spring Boot**
- **Spring Security**
- **OAuth2**
- **MySQL**
- **JWT**
- **Bcrypt**
- **JPA (Java Persistence API)**
- **Docker**

## Setup Instructions

To test this application, you need to generate the necessary keys for JWT token signing. Navigate to the `resources` folder and run the following commands:

```bash
openssl genrsa > app.key
openssl rsa -in app.key -pubout -out app.pub
