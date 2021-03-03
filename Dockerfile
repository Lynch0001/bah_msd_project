FROM openjdk

WORKDIR /APP

COPY ./build/libs/dataservice-0.0.1-SNAPSHOT.jar/APP

EXPOSE 8080

CMD ["java", "-jar", "dataservice-0.0.1-SNAPSHOT.jar"]