package com.examen.ms_pedidos.service;

import com.examen.ms_pedidos.dto.PedidoRequestDTO;
import com.examen.ms_pedidos.entity.Pedido;
import com.examen.ms_pedidos.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoService {
	private final PedidoRepository repository;

    public Pedido crearPedido(PedidoRequestDTO dto) {
        Pedido pedido = new Pedido();
        pedido.setCliente(dto.getCliente());
        pedido.setCorreoCliente(dto.getCorreoCliente());
        pedido.setProductoId(dto.getProductoId());
        pedido.setNombreProducto(dto.getNombreProducto());
        pedido.setCantidad(dto.getCantidad());
        pedido.setPrecioUnitario(dto.getPrecioUnitario());

        return repository.save(pedido);
    }
    
    public List<Pedido> listarTodos() {
        return repository.findAll();
    }
    
    public Pedido buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con ID: " + id));
    }

	public Pedido cambiarEstado(Long id, String nuevoEstado) {
		Pedido pedido = repository.findById(id).orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
	    pedido.setEstado(nuevoEstado);
	    return repository.save(pedido);
	}

	public void eliminarPedido(Long id) {
		if (!repository.existsById(id)) throw new RuntimeException("No se puede eliminar: Pedido no encontrado con ID: " + id);
	    repository.deleteById(id);
	}
}
