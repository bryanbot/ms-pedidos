package com.examen.ms_pedidos.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class PedidoRequestDTO {
	@NotBlank(message = "El cliente es obligatorio")
    private String cliente;
	
	@NotBlank(message = "El correo es obligatorio")
    @Email(message = "Debe proporcionar un formato de correo válido")
    private String correoCliente;
	
    @NotNull(message = "El ID de producto es obligatorio")
    private Long productoId;
    
    @NotNull(message = "El nombre del producto es obligatorio")
    private String nombreProducto;
    
    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad mínima es 1")
    private Integer cantidad;

    @NotNull(message = "El precio unitario es obligatorio")
    @Positive(message = "El precio debe ser positivo")
    private BigDecimal precioUnitario;
}
