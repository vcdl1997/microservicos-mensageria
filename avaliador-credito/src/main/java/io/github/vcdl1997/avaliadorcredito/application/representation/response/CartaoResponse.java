package io.github.vcdl1997.avaliadorcredito.application.representation.response;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CartaoResponse {
	private Long id;
	private String nome;
	private String bandeira;
	private BigDecimal limiteBasico;
}
