FROM java:8
MAINTAINER lidengyin
ARG JAR_FILE
ADD ${JAR_FILE} uaa-server-1.jar
EXPOSE 8212
ENTRYPOINT ["java","-jar","uaa-server-1.jar"]