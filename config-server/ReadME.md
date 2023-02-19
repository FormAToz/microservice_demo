# Config server

The service, what contains the configuration for other services

## Local run

Run following commands in terminal (project folder):

```bash
# create new package
$ mvn clean package

# build named Docker image
$ docker build . -t config-server

# separate run image in background mode (detached, without environment variables)
# env variables definition example: -e "SPRING_PROFILES_ACTIVE=dev"
$ docker run -d -p8071:8071 --name config-server config-server:latest

# get info about all running containers
$ docker ps
```