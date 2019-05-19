FROM openjdk:8-jdk-alpine

WORKDIR /project

COPY /auth-service-impl/target/*.jar auth-service.jar

EXPOSE 5010

CMD java -jar auth-service.jar
