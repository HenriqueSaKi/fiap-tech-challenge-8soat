# ETAPA 1: Compilação do projeto
## Usa uma imagem base como builder
FROM maven:3.8.7-eclipse-temurin-19-alpine AS builder

## Define o diretório de trabalho do builder
WORKDIR /app

## Copia o projeto para o builder
COPY src /app/src
COPY pom.xml /app

## Baixa as dependências do projeto
RUN mvn dependency:go-offline && \
    mvn clean package


# ETAPA 2: Criação da imagem do projeto
## Define a imagem base
FROM openjdk:17-jdk-slim

## Define os argumentos de build
ARG DATASOURCE_URL
ARG DATASOURCE_USERNAME
ARG DATASOURCE_PASSWORD
ARG DATASOURCE_DRIVER_CLASS_NAME

## Define as variáveis de ambiente
ENV DATASOURCE_URL=${DATASOURCE_URL}
ENV DATASOURCE_USERNAME=${DATASOURCE_USERNAME}
ENV DATASOURCE_PASSWORD=${DATASOURCE_PASSWORD}
ENV DATASOURCE_DRIVER_CLASS_NAME=${DATASOURCE_DRIVER_CLASS_NAME}

## Define o diretório de trabalho da imagem
WORKDIR /app

## Copia o projeto compilado no builder para dentro da imagem
COPY --from=builder /app/target/tech-challenge-fase-1.jar .

## Executa a aplicação
ENTRYPOINT ["java", "-jar", "tech-challenge-fase-1.jar"]