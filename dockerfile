FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
ARG APP_FILE
ARG MSG_FILE
ARG DB_FILE
COPY ${JAR_FILE} app.jar
COPY ${APP_FILE} application.properties
COPY ${MSG_FILE} messages.properties
COPY ${DB_FILE} sitedb.mv.db
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]