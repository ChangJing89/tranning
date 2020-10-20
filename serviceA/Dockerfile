FROM docker.io/anapsix/alpine-java
WORKDIR /opt/app/
COPY ./serviceA/target/serviceA-0.0.1-SNAPSHOT.jar /opt/app/
EXPOSE 10001
ENTRYPOINT ["java","-jar", "/opt/app/serviceA-0.0.1-SNAPSHOT.jar"]
