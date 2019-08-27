FROM svenruppert/adopt:1.8.222-10
LABEL maintainer="Sven Ruppert sven.ruppert@gmail.com" description="Desktop To Web - Backend Service Node"

ARG USER_HOME_DIR="/root"
WORKDIR /root

ENV JAVA_HOME /jdk
ENV PATH $JAVA_HOME/bin:$PATH

RUN java -version

COPY ./target/vaadin-app.jar .
EXPOSE 8899
ENTRYPOINT ["java", "-jar", "vaadin-app.jar"]
CMD ["-backendservicePORT", "7000","-backendserviceIP", "backend.desktop-to-web.com", "-port", "8899"]