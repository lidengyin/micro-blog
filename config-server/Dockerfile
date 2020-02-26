FROM java:8
MAINTAINER lidengyin
ARG JAR_FILE
ADD ${JAR_FILE} admin-server.jar
EXPOSE 8205
ENTRYPOINT ["java","-jar","admin-server.jar"]

