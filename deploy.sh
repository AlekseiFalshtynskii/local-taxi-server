#!/usr/bin/env bash

./build.sh

docker-compose rm -f -s
docker-compose up --build
docker rmi $(docker images -f "dangling=true" -q) -f
