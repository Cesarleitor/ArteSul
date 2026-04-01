package com.arteSul.pedidosystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pedido_items")
public class PedidoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    @DecimalMin("1.0")
    private Double quantidade = 1.0;

    @Column(name = "preco_unitario", nullable = false)
    private Double precoUnitario;

    @Column(nullable = false)
    private Double subtotal;



    public PedidoItem(){}
    public PedidoItem(Produto produto, Double quantidade) {
        this.produto = produto;
        setQuantidade(quantidade);
    }

    public void setQuantidade(Double quantidade) {
        if (quantidade == null || quantidade < 1) {
            this.quantidade = 1.0;
        }
        else {
            this.quantidade = quantidade;
        }
    }
}
