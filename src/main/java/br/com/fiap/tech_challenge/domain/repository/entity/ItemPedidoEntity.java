package br.com.fiap.tech_challenge.domain.repository.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "ITEM_PEDIDO")
public class ItemPedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "descricao", nullable = false, length = 100)
    @NotBlank(message = "Informe a descrição")
    private String descricao;

    @Column(name = "valor_unitario", nullable = false)
    @DecimalMin(value = "0.01", message = "O valor unitário deve ser maior que zero")
    private BigDecimal valorUnitario;

    @Column(name = "quantidade", nullable = false)
    @DecimalMin(value = "1", message = "A quantidade deve ser maior que zero")
    private int quantidade;

    @Column(name = "valor_total_item", nullable = false)
    @DecimalMin(value = "0.01", message = "O valor total deve ser maior que zero")
    private BigDecimal valorTotalItem;

    @ManyToOne
    private PedidoEntity pedido;

}
