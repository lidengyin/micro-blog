FROM java:8
MAINTAINER lidengyin
ARG JAR_FILE
ADD ${JAR_FILE} upload-apk.jar
EXPOSE 8207
ENTRYPOINT ["java","-jar","upload-apk.jar"]

