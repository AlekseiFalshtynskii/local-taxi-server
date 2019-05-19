FROM openjdk:8-jdk-alpine

WORKDIR /project

COPY /trip-service-impl/target/*.jar trip-service.jar

EXPOSE 5040

CMD java -jar trip-service.jar
