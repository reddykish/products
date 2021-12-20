FROM openjdk:11
ADD target/product.jar product.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/product.jar"]
