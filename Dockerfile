FROM eclipse-temurin:21
LABEL authors="Belen ITS"
WORKDIR /app
COPY target/IPLTicketBookingAPI.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]