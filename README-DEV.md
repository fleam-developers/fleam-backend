# docker

we are going to use docker compose for containering our services

[what is docker](https://www.youtube.com/watch?v=rOTqprHv1YE)

[docker compose for microservices](https://www.youtube.com/watch?v=Qw9zlE3t8Ko)

install docker from: https://www.docker.com/

#### simple Dockerfile for java spring boot applications 

(store this in corresponding service directory)

```dockerfile
FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
```



#### simple docker-compose.yml for whole project

```yaml
version: '3'
services:
  templte_service:
    build: template_service
    ports:
      ["8080:8080"]
```



#### build docker image

```bash
docker build -t fleam/template_service .
```

#### run the application on docker container

```bash
docker run -p 8080:8080 fleam/template_service
```



# services

1. create spring boot project using Spring Initializr

2. do your work on the code

3. build it

```bash
./mvnw package
```



(now you can run it without docker if you want)

```bash
java -jar target/project_name.jar
```



4. build docker image (as stated in  above docker part)

5. run the app in docker (as stated in above docker part)
6. 
