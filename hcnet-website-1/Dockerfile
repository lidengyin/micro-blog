FROM java:8
MAINTAINER lidengyin
ARG JAR_FILE
ADD ${JAR_FILE} hcnet-website-1.jar
ADD ./simsun.ttc /usr/share/fonts
EXPOSE 8211
ENTRYPOINT ["java","-jar","hcnet-website-1.jar"]