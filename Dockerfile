FROM docker.io/anapsix/alpine-java
WORKDIR C:/app/
COPY ./serviceA/target/serviceA-0.0.1-SNAPSHOT.jar C:/app/
EXPOSE 30001
ENTRYPOINT ["java","-jar", "serviceA-0.0.1-SNAPSHOT.jar"]
