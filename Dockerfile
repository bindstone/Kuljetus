FROM jimador/docker-jdk-8-maven-node as client-builder
WORKDIR /app
COPY . .
RUN mvn clean install -P production

FROM openjdk:8-jdk-alpine
WORKDIR /app
VOLUME /tmp
COPY --from=client-builder /app/target/actuator-client.jar /app/actuator-client.jar
ENTRYPOINT ["java","-jar","./kuljetus-1.0.0-SNAPSHOT.jar"]
