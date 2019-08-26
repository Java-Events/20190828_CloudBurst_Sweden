#!/bin/bash

docker run \
       -it \
       -p 7000:7000 \
       --rm \
       --name desktop-to-web-backend \
       -v "$(pwd)":/usr/src/mymaven \
       -w /usr/src/mymaven \
       svenruppert/adopt:1.8.222-10 \
       java -jar target/backend-app.jar

