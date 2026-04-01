package com.arteSul.pedidosystem.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoItemDTO {

    @NotNull(message = "O ID do produto é obrigatório")
    private Long produtoId;

    private String produtoNome;

    @NotNull(message = "A quantidade é obrigatória")
    @DecimalMin(value = "1.0", message = "A quantidade deve ser no mínimo 1")
    private Double quantidade;

    private Double precoUnitario;

    private Double subtotal;
}
