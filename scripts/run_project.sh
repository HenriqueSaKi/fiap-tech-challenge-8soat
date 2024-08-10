#!/bin/bash
# Encontra e executa o primeiro arquivo .jar no diretório

JAR_FILE=$(find . -maxdepth 1 -type f -name "*.jar" | head -n 1)

if [ -z "$JAR_FILE" ]; then
    echo "Nenhum arquivo .jar encontrado."
else
    echo "Executando o arquivo: $JAR_FILE"
    java -jar "$JAR_FILE"
fi
