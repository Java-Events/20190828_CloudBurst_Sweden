# 20190828_CloudBurst_Sweden
https://www.meetup.com/de-DE/Skane-Azure-User-Group/events/262222031/



## Version 001 - Swing only



## Version 002 - Vaadin only



## Version 003 - Hybrid

This project is split up into 3 parts. The first one is the Backend-Service. This is just a tiny REST Endpoint implemented with JavaLin. mvn clean package will create a fat jar inside the folder **target**.







## Mappingings

Swing -> Vaadin

LoginDialog -> LoginView
LoginService -> LoginService
I18NProvider -> I18NProvider



## Scaling Migration
per Component test in the cloud. 
Desktop scales only on one machine



## Swing
The Swing app is using the MigLayout. You can find the whitepaper 
here: [http://www.miglayout.com/whitepaper.html](http://www.miglayout.com/whitepaper.html)

## Workshop
1. introduce the I18NProvider for the Swing App
1. divide into components, introduce the EventBus
1. 