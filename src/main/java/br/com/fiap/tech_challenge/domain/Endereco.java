package br.com.fiap.tech_challenge.domain;

import lombok.Data;

@Data
public class Endereco {

    String cep;
    String logradouro;
    String complemento;
    String bairro;
    String cidade;
    String estado;

}
