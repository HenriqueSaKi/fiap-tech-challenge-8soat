package br.com.fiap.tech_challenge.infra.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ENDERECO")
public class EnderecoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ClienteEntity cliente;

    @Column(nullable = false, length = 8)
    private String cep;

    @Column(nullable = false, length = 50)
    private String logradouro;

    @Column(length = 30)
    private String complemento;

    @Column(nullable = false, length = 30)
    private String bairro;

    @Column(nullable = false, length = 50)
    private String cidade;

    @Column(nullable = false, length = 30)
    private String estado;

}
