FROM java:8
MAINTAINER lidengyin
ARG JAR_FILE
ADD ${JAR_FILE} monitoring-server.jar
EXPOSE 8202
ENTRYPOINT ["java","-jar","monitoring-server.jar"]

