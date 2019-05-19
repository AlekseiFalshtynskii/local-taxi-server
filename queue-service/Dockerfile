FROM openjdk:8-jdk-alpine

ARG PORT

WORKDIR /project

COPY /queue-service-impl/target/*.jar queue-service.jar

EXPOSE $PORT

CMD java -jar queue-service.jar
