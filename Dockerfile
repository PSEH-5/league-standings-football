FROM openjdk:latest
ADD target/football-app-0.1.0.jar football-app.jar
ENTRYPOINT ["java","-jar","football-app.jar"]
EXPOSE 8080
