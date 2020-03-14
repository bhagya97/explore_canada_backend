FROM java:8
EXPOSE 8083
ADD /target/microservice-0.0.1-SNAPSHOT.jar explore-canada-microservice.jar
ENTRYPOINT ["java","-jar","explore-canada-microservice.jar"]