#!/bin/bash

docker run \
       -it \
       -p 7000:7000 \
       --rm \
       --name desktop-to-web-backend \
       javaevents/desktop-to-web-backend:latest

