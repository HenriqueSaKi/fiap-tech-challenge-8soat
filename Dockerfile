# Usa uma imagem base como builder para baixar as dependencias
FROM maven:3.8.7-eclipse-temurin-19-alpine AS builder

WORKDIR /app

# copia arquivo de configuração do pom.xml e baixa dependencias
COPY src /app/src
COPY pom.xml /app

# baixa as dependencias no pom
RUN mvn dependency:go-offline && \
    mvn clean package

# constrói a imagem final
FROM openjdk:17-jdk-slim

# Define os argumentos de build
ARG DATASOURCE_URL
ARG DATASOURCE_USERNAME
ARG DATASOURCE_PASSWORD
ARG DATASOURCE_DRIVER_CLASS_NAME

# Define as variáveis de ambiente
ENV DATASOURCE_URL=${DATASOURCE_URL}
ENV DATASOURCE_USERNAME=${DATASOURCE_USERNAME}
ENV DATASOURCE_PASSWORD=${DATASOURCE_PASSWORD}
ENV DATASOURCE_DRIVER_CLASS_NAME=${DATASOURCE_DRIVER_CLASS_NAME}

# Define o diretório de trabalho dentro do container
# WORKDIR /app

# copia o resultado de cima aqui
COPY --from=builder /target/tech-challenge-0.0.1-SNAPSHOT.jar .

# Compila o projeto
RUN mvn clean package

# Executa a aplicação
ENTRYPOINT ["java", "-jar", "tech-challenge-0.0.1-SNAPSHOT.jar"]
