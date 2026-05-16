package com.examen.ms_pedidos.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class PedidoRequestDTO {
	@NotBlank(message = "El cliente es obligatorio")
    private String cliente;

    @NotNull(message = "El ID de producto es obligatorio")
    private Long productoId;

    @Min(value = 1, message = "La cantidad mínima es 1")
    private Integer cantidad;

    @NotNull(message = "El precio unitario es obligatorio")
    @Positive(message = "El precio debe ser positivo")
    private BigDecimal precioUnitario;
}
