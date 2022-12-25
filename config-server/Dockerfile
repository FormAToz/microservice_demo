# Шаг 1
# Базовый образ, содержащий среду Java времени выполнения
FROM openjdk:17-slim as build

WORKDIR application

# Добавить информацию о владельце
LABEL maintainer="Andrew Danilov <7.danilov@gmail.com>"

# Файл jar-приложения
ARG JAR_FILE=target/*.jar

# Добавить файл jar приложения в контейнер
COPY ${JAR_FILE} application.jar

RUN java -Djarmode=layertools -jar application.jar extract

# Шаг 2
# Таже среда Java времени выполнения
# Этот новый образ содержит несколько слоев приложения Spring Boot полного файла JAR
FROM openjdk:17-slim

WORKDIR application

# Скопировать каждый слой, полученный командой jarmode
COPY --from=build application/dependencies/ ./
COPY --from=build application/spring-boot-loader/ ./
COPY --from=build application/snapshot-dependencies/ ./
COPY --from=build application/application/ ./

# Использовать org.springframework.boot.loader.JarLauncher для запуска приложения
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]