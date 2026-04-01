package com.arteSul.pedidosystem.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;

import lombok.Setter;


import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pedidos")
@Getter
@Setter
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long vendedorId;


    private LocalDateTime datePedido = LocalDateTime.now();

    private Double total;

    @JsonManagedReference
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PedidoItem> itens;

    // Método auxiliar para garantir a quantidade mínima em todos os itens
    public void validarItens(){
        if (itens != null) {
            for (PedidoItem item : itens) {
                item.setPedido(this);
                item.setQuantidade(item.getQuantidade());
            }
        }
    }







}
