#!/bin/bash

docker run \
       -it \
       -p 8899:8899 \
       --rm \
       --name desktop-to-web-vaadin \
       -v "$(pwd)":/usr/src/mymaven \
       -w /usr/src/mymaven \
       svenruppert/adopt:1.8.222-10 \
       java -jar target/vaadin-app.jar

