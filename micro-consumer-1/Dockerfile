FROM java:8
MAINTAINER lidengyin
ARG JAR_FILE
ADD ${JAR_FILE} micro-consumer-1.jar
ADD ./simsun.ttc /usr/share/fonts
EXPOSE 8213
ENTRYPOINT ["java","-jar","micro-consumer-1.jar"]

