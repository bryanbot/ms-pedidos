package com.examen.ms_pedidos.service;

import com.examen.ms_pedidos.dto.PedidoRequestDTO;
import com.examen.ms_pedidos.entity.Pedido;
import com.examen.ms_pedidos.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class PedidoService {
	private final PedidoRepository repository;

    public Pedido crearPedido(PedidoRequestDTO dto) {
        Pedido pedido = new Pedido();
        pedido.setCliente(dto.getCliente());
        pedido.setProductoId(dto.getProductoId());
        pedido.setCantidad(dto.getCantidad());
        pedido.setPrecioUnitario(dto.getPrecioUnitario());

        BigDecimal total = dto.getPrecioUnitario().multiply(new BigDecimal(dto.getCantidad()));
        pedido.setTotal(total);

        return repository.save(pedido);
    }
}
