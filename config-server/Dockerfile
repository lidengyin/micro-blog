FROM java:8
MAINTAINER lidengyin
ARG JAR_FILE
ADD ${JAR_FILE} config-server.jar
EXPOSE 8200
ENTRYPOINT ["java","-jar","config-server.jar"]

