FROM openjdk:8
COPY ./target/microservice-0.0.1-SNAPSHOT.jar explore-canada-microservice.jar
EXPOSE 5000
CMD ["java","-jar","explore-canada-microservice.jar"]