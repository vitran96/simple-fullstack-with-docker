# NPM with NODE 21 to build client
FROM docker.io/node:21-alpine3.18 AS client

WORKDIR /app

COPY ./ /app/

RUN npm install
RUN npm run build

# Maven with JDK 17 to build server
FROM docker.io/maven:3.8.3-openjdk-17-slim AS server

WORKDIR /app

COPY --from=client /app/ /app/

RUN mvn -pl simpleserver -DskipTests compile package

# JDK 17 and copy the app to the container
FROM docker.io/openjdk:17-jdk-alpine3.13 AS app

WORKDIR /app

# Copy the app to the container
COPY --from=server /app/simpleserver/target/simpleweb-0.0.1-SNAPSHOT.jar /app/server.jar

# Run the app
CMD ["java", "-jar", "server.jar"]
