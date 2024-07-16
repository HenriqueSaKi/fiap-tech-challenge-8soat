package br.com.fiap.tech_challenge.infra.entity;

import br.com.fiap.tech_challenge.domain.enums.TipoTelefone;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "TELEFONE")
public class TelefoneEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ClienteEntity cliente;

    @Column(nullable = false)
    private TipoTelefone tipoTelefone;

    @Column(nullable = false, length = 2)
    private String ddd;

    @Column(nullable = false, length = 9)
    private String numero;

}
