FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/RaviCalculator-1.4.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

