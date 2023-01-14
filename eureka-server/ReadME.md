# Eureka server

Server for registration services

## Local run

Run following commands in terminal (project folder):
```bash
# create new package
$ mvn clean package

# build named Docker image
$ docker build . -t eureka-server

# run image in background mode (detached)
$ docker run -d -p8070:8070 -e "SPRING_PROFILES_ACTIVE=dev" --name eureka-server eureka-server:latest

# get info about all running containers
$ docker ps
```

Set Spring profiles: `dev`
