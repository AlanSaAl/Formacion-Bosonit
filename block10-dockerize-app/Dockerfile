FROM openjdk:19
EXPOSE 8081
COPY /target/*jar /usr/local/lib/spring.jar
ENTRYPOINT ["java","-jar","/usr/local/lib/spring.jar"]