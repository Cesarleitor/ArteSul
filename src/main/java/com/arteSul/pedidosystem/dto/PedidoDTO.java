package com.arteSul.pedidosystem.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {

    private Long id;

    @NotNull(message = "O ID do vendedor e obrigatório")
    private Long vendedorId;



    @NotNull(message = "O pedido deve conter ao menos um item!")
    @Size(min = 1, message = "O pedido deve conter ao menos um produto!")
    private List<PedidoItemDTO> itens; // só o ID do produto

    private LocalDateTime dataPedido;

    private Double total; // pode ser calculado no service
}
