package com.examen.ms_pedidos.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pedidos")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Pedido {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotBlank(message = "El nombre del cliente es obligatorio")
    private String cliente;
    
    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "Debe proporcionar un formato de correo válido")
    private String correoCliente;
    
    @NotNull(message = "El ID del producto es obligatorio")
    private Long productoId;
    
    @NotNull(message = "El nombre del producto es obligatorio")
    private String nombreProducto;
    
    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad debe ser al menos 1")
    private Integer cantidad;
    
    @NotNull(message = "El precio unitario es obligatorio")
    @DecimalMin(value = "0.01", message = "El precio debe ser mayor a 0")
    private BigDecimal precioUnitario;
    
    @Column(precision = 10, scale = 2)
    private BigDecimal total;
    
    private String estado;

    @Column(name = "fecha_pedido", updatable = false)
    private LocalDateTime fechaPedido;

    @PrePersist
    protected void onCreate() {
        this.fechaPedido = LocalDateTime.now();
        if (this.cantidad != null && this.precioUnitario != null) this.total = this.precioUnitario.multiply(new BigDecimal(this.cantidad));
        if (this.estado == null) this.estado = "REGISTRADO";
    }
}
