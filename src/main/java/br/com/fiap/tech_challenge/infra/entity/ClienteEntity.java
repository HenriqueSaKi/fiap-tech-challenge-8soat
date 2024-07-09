package br.com.fiap.tech_challenge.infra.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "CLIENTE")
public class ClienteEntity {

    @Id
    @Column(nullable = false, length = 11)
    private String cpf;

    @Column(nullable = false, length = 100)
    private String nomeCompleto;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 20)
    private String telefone;

}
