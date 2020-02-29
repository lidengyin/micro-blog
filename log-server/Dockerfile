FROM java:8
MAINTAINER lidengyin
ARG JAR_FILE
ADD ${JAR_FILE} log-server.jar
EXPOSE 8206
ENTRYPOINT ["java","-jar","log-server.jar"]

