FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
CMD ["java","-Dspring.data.mongodb.uri=mongodb://mongodna:27017/test","-jar","/app.jar"]