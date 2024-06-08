FROM eclipse-temurin:17
EXPOSE 8080
ADD build/libs/MedicalService*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]