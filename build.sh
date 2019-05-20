#!/usr/bin/env bash

cd ./eureka-service
mvn clean package deploy -DskipTests

cd ../zuul-service
mvn clean package deploy -DskipTests

cd ../turbine-service
mvn clean package deploy -DskipTests

cd ../auth-service
mvn clean package deploy -DskipTests

cd ../queue-service
mvn clean package deploy -DskipTests

cd ../trip-service
mvn clean package deploy -DskipTests

cd ../../local-taxi-client
npm i

cd ../..
