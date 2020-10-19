FROM docker.io/anapsix/alpine-java
WORKDIR /opt/app/
COPY C:/tranning/serviceA/target/serviceA-0.0.1-SNAPSHOT.jar /opt/app/
EXPOSE 30001
ENTRYPOINT ["java","-jar", "serviceA-0.0.1-SNAPSHOT.jar"]
