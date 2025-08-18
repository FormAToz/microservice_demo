
# Microservice demo

Demo of microservice interaction.

## Stack

* Java 17
* Spring Boot
* Lombok
* Mapstruct
* PostgreSQL

## How to use

**Clone repository from GitHub:**
```bash
$ git clone https://github.com/FormAToz/microservice_demo.git
```
FYI: all services have ReadMe-file with named commands.


**Create Docker images for all services (run commands inside each service project folder):**
```bash
# create new package
$ mvn clean package

# build named Docker image
$ docker build . -t <service-name>
```


**Create Docker image for PostgreSql:**
```bash
# pull image from DockerHub
$ docker pull postgres
```


**Create Docker image for Keycloak:**
```bash
# pull image from DockerHub
$ docker pull quay.io/keycloak/keycloak:latest

# run the container with Admin rights
$ docker run -p 8080:8080 -e KC_BOOTSTRAP_ADMIN_USERNAME=admin -e KC_BOOTSTRAP_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:latest start-dev --http-port=8080

# access the Keycloak Admin Console
$ http://localhost:8080
```


**Get back to microservice-demo/dev project folder and run all services via Docker compose:**
```bash
$ cd microservice-demo/dev

$ docker-compose up -d

# get info about all running containers
$ docker ps
```
