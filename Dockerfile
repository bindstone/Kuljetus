FROM bindstone/bs-vaadin-builder-15:latest as client-builder
WORKDIR /app
COPY ./pom.xml ./pom.xml
RUN mvn dependency:resolve

COPY ./frontend ./frontend
COPY ./src ./src
COPY ./tsconfig.json ./tsconfig.json
COPY ./types.d.ts ./types.d.ts
COPY ./webpack.config.js ./webpack.config.js
RUN ls
RUN mvn clean package -P production

FROM openjdk:14-jdk-buster
WORKDIR /app
VOLUME /tmp
COPY --from=client-builder /app/target/kuljetus-1.0.0-SNAPSHOT.jar /app/kuljetus.jar
ENTRYPOINT ["java","-jar -Xms1g -Xmx2g","./kuljetus.jar"]
