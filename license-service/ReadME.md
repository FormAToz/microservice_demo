# License service

A service, that provides information about licenses

## Local run

Run following commands in terminal (project folder):
```bash
# create new package
$ mvn clean package

# build named Docker image
$ docker build . -t license-service

# separate run image in background mode (detached, without environment variables)
# env variables definition example: -e "SPRING_PROFILES_ACTIVE=dev"
$ docker run -d -p8071:8071 --name license-service license-service:latest

# get info about all running containers
$ docker ps
```

Set Spring profiles: `dev,local`
