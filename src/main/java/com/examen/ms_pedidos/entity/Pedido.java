package com.examen.ms_pedidos.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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

    private String cliente;
    
    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "Debe proporcionar un formato de correo válido")
    private String correoCliente;
    
    private Long productoId;
    private Integer cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal total;
    private String estado; // REGISTRADO, PAGADO, ENTREGADO

    @Column(updatable = false)
    private LocalDateTime fechaPedido;

    @PrePersist
    protected void onCreate() {
        this.fechaPedido = LocalDateTime.now();
        if (this.estado == null) this.estado = "REGISTRADO";
    }
}
