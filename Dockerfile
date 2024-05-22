FROM openjdk:17-jdk-alpine
COPY target/shorturl-0.0.1.jar application.jar
ENTRYPOINT ["java", "-jar", "application.jar"]