FROM gradle:jdk11-focal
COPY "./target/DemoApplication-01.jar" "app.jar"
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]