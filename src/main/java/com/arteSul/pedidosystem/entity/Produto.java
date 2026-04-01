package com.arteSul.pedidosystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(nullable = false)
    private String nome;

    @Size(max = 2)
    @Column(length = 2)
    private String tamanho;

    @NotNull
    @DecimalMin("0.01")
    @Column(nullable = false)
    private Double preco;
}
