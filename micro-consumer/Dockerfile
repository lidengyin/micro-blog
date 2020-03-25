FROM java:8
MAINTAINER lidengyin
ARG JAR_FILE
ADD ${JAR_FILE} micro-consumer.jar
ADD ./simsun.ttc /usr/share/fonts
EXPOSE 8209
ENTRYPOINT ["java","-jar","micro-consumer.jar"]

