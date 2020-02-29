FROM java:8
MAINTAINER lidengyin
ARG JAR_FILE
ADD ${JAR_FILE} user-service.jar
EXPOSE 8201
ENTRYPOINT ["java","-jar","user-service.jar"]

