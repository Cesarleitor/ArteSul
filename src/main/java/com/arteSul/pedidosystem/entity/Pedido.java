package com.arteSul.pedidosystem.entity;

import jakarta.persistence.*;
import lombok.Getter;

import lombok.Setter;


import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long vendedorId;


    private LocalDateTime datePedido = LocalDateTime.now();

    private Double total;

    @OneToMany(cascade = CascadeType.ALL)
    private List<PedidoItem> itens;


    public void setDataPedido(LocalDateTime localDateTime) {
    }
}
