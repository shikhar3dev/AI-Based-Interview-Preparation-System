# AI-Based Interview Preparation System (Spring Boot)

This repository contains a Java 17 + Spring Boot 3 backend implementation for an AI-based interview preparation platform.

## Implemented Modules

- JWT authentication with BCrypt password hashing
- Interview session management APIs
- AI integration service abstraction (via `WebClient`)
- Performance scoring and feedback persistence
- MySQL persistence with Spring Data JPA

## API Endpoints

### Authentication
- `POST /api/auth/register`
- `POST /api/auth/login`

### Interview
- `POST /api/interviews`
- `GET /api/interviews/history/{userId}`

## Run Locally

```bash
mvn spring-boot:run
```

Update database and JWT settings in:

- `src/main/resources/application.yml`
