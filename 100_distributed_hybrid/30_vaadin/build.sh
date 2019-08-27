#!/bin/bash
docker build -t javaevents/desktop-to-web-vaadin .
#docker tag javaevents/desktop-to-web-vaadin:latest javaevents/desktop-to-web-vaadin:20190826-001
#docker push javaevents/desktop-to-web-vaadin:20190826-001
docker push javaevents/desktop-to-web-vaadin:latest
