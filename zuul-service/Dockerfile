FROM openjdk:8-jdk-alpine

WORKDIR /project

COPY /target/*.jar zuul-service.jar

EXPOSE 5000

CMD java -jar zuul-service.jar
