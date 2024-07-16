# PosTech - Software Architecture
#### Projeto relacionado ao Tech Challenge fornecido como atividade avaliativa do curso de pós graduação em Arquitetura de Software da FIAP.

### Como executar a aplicação?
1. Clone o projeto na sua máquina </br>
``git clone https://github.com/HenriqueSaKi/fiap-tech-challenge-8soat.git``
2. Instale as dependências </br>
``mvn clean install``
3. Inclua as variáveis de ambiente relacionados ao banco de dados na sua IDE. </br>
   <details>
      <summary><b>Variáveis necessárias no projeto</b></summary>

      ```
      DATASOURCE_URL=
      DATASOURCE_USERNAME=
      DATASOURCE_PASSWORD=
      DATASOURCE_DRIVER_CLASS_NAME=
      ```

   </details>

   **Observação:** Caso não tenha uma preferência de banco de dados, recomendo utilizar o banco de dados H2. Na seção [Executando a aplicação com banco de dados H2](#executando-a-aplicacao-com-banco-de-dados-h2), te explico como fazer isso.
   
4. Tudo pronto, só dar um <i>Run</i> :arrow_forward: na aplicação!

### Executando a aplicação com banco de dados H2
<details>
   <summary>Explicação de como utilizar o H2, clique para expandir.</summary>

   Conforme comentado acima, temos a opção de configurar um simples banco de dados H2 na nossa aplicação, para poder rodar e executar testes locais.

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

### Documentação:
Após conseguir subir a aplicação local, você poderá acessar a documentação a através do link: 
[Swagger-UI](http://localhost:8080/api/v1/swagger-ui/index.html)
