# Simple Web with Docker

## Prerequisites

- [Docker](https://docs.docker.com/get-docker/)
- [Docker Compose](https://docs.docker.com/compose/install/)
- [Maven](https://maven.apache.org/install.html)
- [Node.js](https://nodejs.org/en/download/)
- [Java 17](https://www.oracle.com/java/technologies/downloads/#java17)
- [Spring Boot 3.1.8](https://spring.io/projects/spring-boot)
- [PostgreSQL](https://www.postgresql.org/download/)
- [dbeaver](https://dbeaver.io/download/)

## Setup this project from scratch
```bash
# Create Spring server from web starter
# Or use command:
spring init --dependencies=web,picocli,postgresql,devtools,security --build=maven --name=simpleserver --java-version=17 --spring-boot-version=3.1.8 simpleserver
tar -xvf simpleserver.tar.gz

# Create Angular app
# SCSS
# No telemetry
# SCSS
ng new simplefe --style=scss --skip-telemetry --strict=true

```

## Start development
```bash
# Start server
mvn compile spring-boot:run -pl simpleserver

# npm
```