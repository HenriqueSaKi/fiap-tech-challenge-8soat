package br.com.fiap.tech_challenge.infra.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

import br.com.fiap.tech_challenge.domain.enums.CategoriaProduto;


//Fixme - Renomear para ProdutoEntity
//Fixme - Tirar de infra pois trata-se de uma classe de negócio
@Entity
@Data
@Table(name = "PRODUTO")
public class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID produtoId;

    @Column(name = "nome_produto", nullable = false, length = 50)
    @NotBlank(message = "Informe o nome do produto")
    private String nomeProduto;

    @Column(name = "descricao_produto", nullable = false, length = 200)
    private String descricaoProduto;

    @Column(name = "preco_produto", nullable = false)
    @DecimalMin(value = "0.01", message = "O preço deve ser maior que zero")
    @Digits(integer = 10, fraction = 2)
    private BigDecimal precoProduto;

    @Enumerated(EnumType.STRING)
    @Column(name = "categoria", nullable = false)
    private CategoriaProduto categoriaProduto;
}