
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


**Get back to microservice-demo/dev project folder and run all services via Docker compose:**
```bash
$ cd microservice-demo/dev

$ docker-compose up -d

# get info about all running containers
$ docker ps
```
