# Organization service

A service, that provides information about organizations

## Local run

Run following commands in terminal (project folder):
```bash
# create new package
$ mvn clean package

# build named Docker image
$ docker build . -t organization-service

# separate run image in background mode (detached, without environment variables)
# env variables definition example: -e "SPRING_PROFILES_ACTIVE=dev"
$ docker run -d -p8081:8081 --name organization-service organization-service:latest

# get info about all running containers
$ docker ps
```

Set Spring profiles: `dev,local`
