package com.examen.ms_pedidos.controller;

import com.examen.ms_pedidos.dto.PedidoRequestDTO;
import com.examen.ms_pedidos.entity.Pedido;
import com.examen.ms_pedidos.service.PedidoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PedidoController {
	private final PedidoService service;

    @PostMapping
    public ResponseEntity<Pedido> crear(@Valid @RequestBody PedidoRequestDTO request) {
        Pedido nuevoPedido = service.crearPedido(request);
        return new ResponseEntity<>(nuevoPedido, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> listar() {
    	List<Pedido> pedidos = service.listarTodos();
        return ResponseEntity.ok(pedidos);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> obtenerPorId(@PathVariable Long id) {
        Pedido pedido = service.buscarPorId(id);
        return ResponseEntity.ok(pedido);
    }
    
    @PatchMapping("/{id}/estado")
    public ResponseEntity<Pedido> actualizarEstado(@PathVariable Long id, @RequestBody Map<String, String> body) {
        String nuevoEstado = body.get("estado");
        Pedido pedidoActualizado = service.cambiarEstado(id, nuevoEstado);
        return ResponseEntity.ok(pedidoActualizado);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminarPedido(id);
        return ResponseEntity.noContent().build();
    }
}
