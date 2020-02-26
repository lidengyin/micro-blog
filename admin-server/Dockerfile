FROM java:8
MAINTAINER lidengyin
ARG JAR_FILE
ADD ${JAR_FILE} gateway-server.jar
EXPOSE 8204
ENTRYPOINT ["java","-jar","gateway-server.jar"]

