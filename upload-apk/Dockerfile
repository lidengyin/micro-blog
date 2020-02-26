FROM java:8
MAINTAINER lidengyin
ARG JAR_FILE
ADD ${JAR_FILE} uaa-server.jar
EXPOSE 8203
ENTRYPOINT ["java","-jar","uaa-server.jar"]

