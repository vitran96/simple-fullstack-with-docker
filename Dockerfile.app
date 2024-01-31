# JDK 17 and copy the app to the container
FROM docker.io/openjdk:17-jdk-alpine3.13 AS app

WORKDIR /app

# Copy the app to the container
COPY simpleserver/target/simpleweb-0.0.1-SNAPSHOT.jar server.jar

# Run the app
CMD ["java", "-jar", "server.jar"]
