package br.com.fiap.tech_challenge.adapters.driven.infrastructure.entity;

import br.com.fiap.tech_challenge.core.domain.model.enums.SituacaoPedido;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "PEDIDO")
public class PedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "data_pedido", nullable = false)
    private Date dataPedido;

    @Column(name = "valor_total_pedido", nullable = false)
    private BigDecimal valorTotalPedido;

    @Column(name = "situacao", nullable = false)
    private SituacaoPedido situacao;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "pedido_id")
    private List<ItemPedidoEntity> itensPedido = new ArrayList<>();

    @ManyToOne
    private ClienteEntity cliente;

}
