# PosTech - Software Architecture
#### Projeto relacionado ao Tech Challenge fornecido como atividade avaliativa do curso de pós graduação em Arquitetura de Software da FIAP.

## Objetivos
Desenvolver um sistema para uma lanchonete, seguindo os pré-requisitos do Tech Challenge.

## Pré-requisitos do Tech Challenge:
#### Arquitetura:
&emsp;Uma das premissas para esse projeto, foi desenvolver um sistema monolito, seguindo a arquitetura hexagonal.</br>
&emsp;Ou seja, a ideia principal é conseguir fornecer um sistema que favorece reusabilidade de código, alta coesão, baixo acoplamento, independência de tecnologia e que são mais fáceis de serem testados.

#### API's:
- Cadastro do Cliente
- Identificação do Cliente via CPF
- Criar, editar e remover produtos
- Buscar produtos por categoria
- Fake checkout, apenas enviar os produtos escolhidos para a fila. O checkout é a finalização do pedido.
- Listar os pedidos

### Banco de dados
De livre escolha (utilizamos o MySQL).

## Pré-requisitos para executar os comandos Docker:
<details>
  <summary>Clique para expandir</summary>
  Você precisa ter o docker e o docker-compose instalados na sua máquina para poder rodar o projeto.

As instruções executadas no próximo tópico foram testadas com:
- Linux Ubuntu 22.04.4 LTS;
- Docker 27.0.2;
- Docker Compose 1.26.0.
</details>

## Como executar a aplicação?
1. Clone o projeto na sua máquina </br>
   ``git clone https://github.com/HenriqueSaKi/fiap-tech-challenge-8soat.git``
#### &emsp;&emsp;Obs: Certifique-se ter o git instalado em sua máquina e execute o comando abaixo pelo terminal.

2. Crie as imagens e suba os containeres:</br>
   **Acesse a pasta raiz do projeto e execute os comandos abaixo:**

      ```sh
      # Crie a imagem docker
      docker-compose build --no-cache 
      
      # Suba o container
      docker-compose up -d
      
      # Confira se o container foi iniciado corretamente
      # O status deve estar 'Up'
      docker-compose ps -a
      ```

3. Acesse o Swagger da aplicação, pelo link abaixo:</br>
   http://localhot:8080/api/v1/swagger-ui/index.html

## Outras dicas relacionadas ao Docker, aplicação e banco de dados.

#### 1. [Docker] Comandos para reiniciar os containeres
```sh
# Para os containeres
docker-compose down

# Recria as imagens e sobe tudo novamente
docker-compose build --no-cache && docker-compose up -d
```

#### 2. [Docker] Se desejar voltar o banco de dados ao seu estado inicial, apague os volume de dados
```sh
# Para os containeres
docker-compose down

# Recria as imagens e sobe tudo novamente
docker-compose build --no-cache && docker-compose up -d
```

#### 3. [Docker] Use os comandos abaixo para visualizar os logs dos containeres
```sh
docker logs bd_lanchonete
docker logs app_lanchonete
```

#### 4. [Banco de dados] Após subir o container, caso queira testar apenas o MySQL, abra o terminal e execute:
```sh
docker exec -it bd_lanchonete mysql -u user_fiap -p
use lanchonete;
show tables;
```

#### 5. [Aplicação] Se desejar alterar a aplicação, será necessário gerar um novo arquivo .jar na pasta app/target
O comando abaixo compila, testa e empacota (cria o .jar) da aplicação</br>
``mvn clean install``</br>
Após usá-lo, reinicialize os containeres</br>

#### 6. [Aplicação] Caso queira executar apenas a aplicação localmente, siga os passos abaixo:
1. Instale as dependências </br>
   ``mvn clean install``
2. Inclua as variáveis de ambiente relacionados ao banco de dados na sua IDE. </br>
      ```
      DATASOURCE_URL=
      DATASOURCE_USERNAME=
      DATASOURCE_PASSWORD=
      DATASOURCE_DRIVER_CLASS_NAME=
      ```

   **Observação:** Caso não tenha uma preferência de banco de dados, recomendo utilizar o banco de dados H2.
   <details>
     <summary>Explicação de como utilizar o H2, clique para expandir.</summary>

   O primeiro passo para configurarmos o banco será adicionar a dependência do H2 no arquivo pom.xml
     ``` XML
     <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <scope>runtime</scope>
     </dependency>
  
     ```

   Feito isso, podemos alterar nosso arquivo application.yml, com as informações padrões do H2.
     ``` YAML
     spring:
        application:
           name: tech-challenge
        datasource:
           url: ${DATASOURCE_URL:jdbc:h2:mem:testdb}
           username: ${DATASOURCE_USERNAME:sa}
           password: ${DATASOURCE_PASSWORD:password}
           driverClassName: ${DATASOURCE_DRIVER_CLASS_NAME:org.h2.Driver}
     ```
   **Observação:** Mantendo as configurações dessa forma, você permite que por padrão o banco de dados utilizado seja o H2. No entanto, caso você informe na sua IDE as variáveis de ambiente de acordo com o banco de dados de sua preferência e incluir as dependências necessárias, você poderá utilizar inúmeras opções de banco de dados relacional.

   Por fim, podemos habilitar a visualização do console do H2 adicionando a seguinte configuração:
     ``` YAML
     spring:
        h2:
           console:
              enabled: true
              path: /h2-console
     ```

   **Observação:** Após subir sua aplicação com o banco de dados H2, você poderá acessar o console do banco de dados através desse link: http://localhost:8080/api/v1/h2-console

  </details>

4. Tudo pronto, só dar um <i>Run</i> :arrow_forward: na aplicação!

