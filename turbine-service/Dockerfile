FROM openjdk:8-jdk-alpine

WORKDIR /project

COPY /target/*.jar turbine-service.jar

EXPOSE 5050

CMD java -jar turbine-service.jar
