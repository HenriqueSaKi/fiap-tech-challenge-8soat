package br.com.fiap.tech_challenge.infra.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "CLIENTE")
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cpf", nullable = false, length = 11)
    private String cpf;

    @Column(name = "nome_completo", nullable = false, length = 100)
    private String nomeCompleto;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente_id")
    private List<TelefoneEntity> telefones = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente_id")
    private List<EnderecoEntity> enderecos = new ArrayList<>();

}
