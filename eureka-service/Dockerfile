FROM openjdk:8-jdk-alpine

WORKDIR /project

COPY /target/*.jar eureka-service.jar

EXPOSE 8282

CMD java -jar eureka-service.jar
