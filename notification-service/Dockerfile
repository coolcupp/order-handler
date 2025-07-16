FROM amazoncorretto:17

WORKDIR /app

COPY target/notification-service.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]