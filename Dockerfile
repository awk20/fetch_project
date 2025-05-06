#FROM openjdk:17-jdk-alpine
FROM amazoncorretto:21-alpine
LABEL authors="anthonyknapik"

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app.jar"]