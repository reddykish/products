FROM openjdk:11
VOLUME /tmp
COPY target/*.jar product.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/product.jar"]