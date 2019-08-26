#!/bin/bash
docker build -t javaevents/desktop-to-web-backend .
#docker tag javaevents/desktop-to-web-backend:latest javaevents/desktop-to-web-backend:20190826-001
#docker push javaevents/desktop-to-web-backend:20190826-001
docker push javaevents/desktop-to-web-backend:latest
