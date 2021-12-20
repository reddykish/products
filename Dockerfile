FROM openjdk:11
COPY target/product.jar product.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/product.jar"]
