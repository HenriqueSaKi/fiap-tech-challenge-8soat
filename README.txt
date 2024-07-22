# PosTech - Software Architecture
#### Projeto relacionado ao Tech Challenge fornecido como atividade avaliativa do curso de pós graduação em Arquitetura de Software da FIAP.

## OBJETIVOS
Desenvolver um sistema para uma lanchonete.  
Uma aplicação backend (monolito) seguindo os padrões apresentados nas aulas.

### Utilizando arquitetura hexagonal
Sistemas que favorecem reusabilidade de código, alta coesão, baixo acoplamento, independência de tecnologia e que são mais fáceis de serem testados.

### APIs
A aplicação deverá conter uma api para:
- Cadastro do Cliente
- Identificação do Cliente via CPF
- Criar, editar e remover produtos
- Buscar produtos por categoria
- Fake checkout, apenas enviar os produtos escolhidos para a fila. O checkout é a finalização do pedido.
- Listar os pedidos

### Banco de dados
De livre escolha (utilizamos o MySQL).

## INSTRUÇÕES PARA RODAR O PROJETO

O projeto possui dois containeres:
- um para o banco de dados (MySQL 8.4.0);
- e outro para a aplicação back-end (Java 17).

O banco de dados já foi previamente populado com alguns dados de exemplo de uso.

A aplicação pode ser testada pela API (endpoints), que está documentada com o Swagger.  
Não foi desenvolvido o front-end, que está fora do escopo deste projeto.

### Pré-requisitos
Você precisa ter o docker e o docker-compose instalados na sua máquina para poder rodar o projeto.
Além disso, vai precisar do git também para clonar o projeto.

As instruções a seguir foram testadas com:
- Linux Ubuntu 22.04.4 LTS;
- Docker 27.0.2;
- Docker Compose 1.26.0.

###  Clone o projeto na sua máquina
Certifique-se ter o git instalado em sua máquina e execute o comando abaixo pelo terminal. <br />
``git clone https://github.com/HenriqueSaKi/fiap-tech-challenge-8soat.git``


###  Crie as imagens e suba os containeres
Execute os comandos abaixo pelo terminal na pasta raiz do projeto:

# Crie a imagem docker e suba o container
docker-compose build --no-cache && docker-compose up -d

# Confira se o container foi iniciado corretamente
# O status deve estar 'Up'
docker-compose ps -a

# Teste a aplicação em um navegador qualquer pelo link:
http://localhost:8080/api/v1/swagger-ui/index.html

# Caso queira testar apenas o MySQL, abra o terminal e execute:
docker exec -it bd_lanchonete mysql -u user_fiap -p
use lanchonete;
show tables;

###  Outras informações úteis

# Comandos para reiniciar os containeres
# Para e remove os containeres, recria as imagens e sobe tudo novamente
docker-compose down
docker-compose build --no-cache && docker-compose up -d

# Se desejar alterar a aplicação, será necessário gerar um novo arquivo .jar na pasta app/target
# O comando abaixo compila, testa e empacota (cria o .jar) da aplicação
# Após usá-lo, reinicialize os containeres
mvn clean install

# Se desejar alterar o schema do banco de dados, é só alterar o arquivo script.sql na pasta bd e reiniciar os containeres

# Se desejar voltar o banco de dados ao seu estado inicial, apague os volume de dados
docker-compose down
docker volume rm bd_lanchonete_dados
docker-compose build --no-cache && docker-compose up -d

# Use os comandos abaixo para visualizar os logs dos containeres
docker logs bd_lanchonete
docker logs app_lanchonete
