FROM java:8
MAINTAINER lidengyin
ARG JAR_FILE
ADD ${JAR_FILE} hcnet-website.jar
EXPOSE 8210
ENTRYPOINT ["java","-jar","hcnet-website.jar"]

