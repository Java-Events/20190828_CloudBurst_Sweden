FROM svenruppert/adopt:1.8.222-10
LABEL maintainer="Sven Ruppert sven.ruppert@gmail.com" description="Desktop To Web - Backend Service Node"

ARG USER_HOME_DIR="/root"
WORKDIR /root

ENV JAVA_HOME /jdk
ENV PATH $JAVA_HOME/bin:$PATH

RUN java -version

COPY ./target/backend-app.jar .
EXPOSE 7000
ENTRYPOINT ["java", "-jar", "backend-app.jar"]
CMD ["-port", "7000"]