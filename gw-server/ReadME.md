# Gateway server

A server, that routes all requests.

## Local run

Run following commands in terminal (project folder):
```bash
# create new package
$ mvn clean package

# build named Docker image
$ docker build . -t gw-server

# separate run image in background mode (detached, without environment variables)
# env variables definition example: -e "SPRING_PROFILES_ACTIVE=dev"
$ docker run -d -p8072:8072 --name gw-server gw-server:latest

# get info about all running containers
$ docker ps
```

Set Spring profiles: `dev,local`
