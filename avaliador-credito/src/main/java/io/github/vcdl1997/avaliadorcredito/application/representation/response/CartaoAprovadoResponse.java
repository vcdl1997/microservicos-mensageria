package io.github.vcdl1997.avaliadorcredito.application.representation.response;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CartaoAprovadoResponse {
	private String cartao;
	private String bandeira;
	private BigDecimal limiteAprovado;
}
