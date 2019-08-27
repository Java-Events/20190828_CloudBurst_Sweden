# 20190828_CloudBurst_Sweden
https://www.meetup.com/de-DE/Skane-Azure-User-Group/events/262222031/



## Version 001 - Swing only



## Version 002 - Vaadin only



## Version 003 - Hybrid

This project is split up into 3 parts. The first one is the Backend-Service. This is just a tiny REST Endpoint implemented with JavaLin. mvn clean package will create a fat jar inside the folder **target**.

### Shared

This are the shared implementations. Build this with **mvn clean install** to push the jar into your local **.m2**

### Backend

This is the REST Endpoint that will be consumed from Swing and Vaadin. **mvn clean package** is enough, because we don´t need the jar for an other build. Inside the **target** folder you will find a fat jar named **backend-app.jar** Default port for the REST Endpoint will be the port **7000**.

### Swing

The Swing app could be build with **mvn clean package**. Inside the **target** folder you will find a fat jar with the name **swing-app.jar** If you are running the REST Endpoint on your local machine, you could just start the app.



### Vaadin

The Vaadinapp could be build with **mvn clean package**. Inside the **target** folder you will fina a fat jar called **vaadin-app.jar**. If you are running the REST Endpoint on your local machine, you could just start the app. The Servlet-container will run on port **8899**

















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