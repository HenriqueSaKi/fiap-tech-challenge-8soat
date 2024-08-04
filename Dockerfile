# Usa uma imagem base do OpenJDK 17
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
WORKDIR /app

# Copia o projeto para o diretório de trabalho
COPY . /app

# Compila o projeto
RUN ./mvnw clean package

# Executa a aplicação
ENTRYPOINT ["java", "-jar", "./target/tech-challenge-0.0.1-SNAPSHOT.jar"]
