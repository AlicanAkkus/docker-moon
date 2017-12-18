FROM java:8-alpine
VOLUME /data
ADD target/docker-moon-0.0.1-SNAPSHOT.jar docker-moon.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/docker-moon.jar"]