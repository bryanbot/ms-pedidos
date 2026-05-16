package com.examen.ms_pedidos;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootTest
class MsPedidosApplicationTests {
	
	@Autowired
    private MockMvc mockMvc;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	void debeCalcularTotalCorrectamente() throws Exception {
	    String nuevoPedido = "{"
            + "\"cliente\":\"Bryan\","
            + "\"correoCliente\":\"bryan@test.com\","
            + "\"productoId\": 2,"
            + "\"cantidad\": 2,"
            + "\"precioUnitario\": 50.0"
            + "}";
	    
	    mockMvc.perform(post("/api/pedidos")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(nuevoPedido))
	            .andExpect(status().isCreated())
	            .andExpect(jsonPath("$.total").value(100.0))
	    		.andExpect(jsonPath("$.cliente").value("Bryan"));
	}
	
	@Test
	void noDebePermitirCorreoInvalido() throws Exception {
        String pedidoInvalido = "{"
            + "\"cliente\":\"Bryan\","
            + "\"correoCliente\":\"correo-mal-formado\"," // Email inválido
            + "\"productoId\": 1,"
            + "\"cantidad\": 1,"
            + "\"precioUnitario\": 10.0"
            + "}";

        mockMvc.perform(post("/api/pedidos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(pedidoInvalido))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors.correoCliente").exists());
    }
	
	@Test
	void noDebePermitirCantidadNegativa() throws Exception {
        String pedidoInvalido = "{\"cliente\":\"Bryan\", \"correoCliente\":\"test@test.com\", \"productoId\": 1, \"cantidad\": -1, \"precioUnitario\": 10.0}";

        mockMvc.perform(post("/api/pedidos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(pedidoInvalido))
                .andExpect(status().isBadRequest());
    }
	
	@Test
	void debeRetornar404SiPedidoNoExiste() throws Exception {
        mockMvc.perform(get("/api/pedidos/99999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").exists());
    }
}
