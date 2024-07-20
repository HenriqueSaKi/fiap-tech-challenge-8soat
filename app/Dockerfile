# Imagem base
FROM eclipse-temurin:17-jdk-alpine

# Configurar o diretório no contêiner
WORKDIR /app

# Copiar o arquivo JAR da aplicação para o container
COPY target/tech-challenge-0.0.1-SNAPSHOT.jar app.jar

# Porta que a aplicação irá rodar
EXPOSE 8080

# Executar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
