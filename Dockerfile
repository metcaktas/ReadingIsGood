FROM amazoncorretto:11-alpine-jdk
VOLUME /tmp
COPY target/*.jar books.jar
ENTRYPOINT ["java","-jar","/books.jar"]