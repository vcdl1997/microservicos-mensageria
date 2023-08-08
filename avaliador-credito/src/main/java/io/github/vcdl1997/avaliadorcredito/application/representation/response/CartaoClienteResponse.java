package io.github.vcdl1997.avaliadorcredito.application.representation.response;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartaoClienteResponse {
	private String nome;
	private String bandeira;
	private BigDecimal limiteLiberado;
}
