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
ng new --no-create-application
ng generate application simplefe --style=scss

# ...
```

## Start development
```bash
# Start server
mvn compile spring-boot:run -pl simpleserver

# npm
npm watch
```

## Build and Package a Docker image
```bash
# Build
mvn -pl simpleserver clean

npm install
npm run build

mvn -pl simpleserver -DskipTests compile package

docker build -f Dockerfile.app -t simpleweb .
```

## Build within a Docker container
```bash
docker build -t simpleweb .
```

## Run the Docker image
```bash
docker run -p 8080:8080 simpleweb
```
