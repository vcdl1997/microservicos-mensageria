package io.github.vcdl1997.cartoes.application.representation.request;

import java.math.BigDecimal;

import io.github.vcdl1997.cartoes.domain.BandeiraCartao;
import io.github.vcdl1997.cartoes.domain.Cartao;
import lombok.Data;

@Data
public class CartaoRequest {

	private String nome;
	
	private BandeiraCartao bandeira;
	
	private BigDecimal renda;
	
	private BigDecimal limite;

	public Cartao fromModel() {
		return new Cartao(
			nome,
			bandeira,
			renda,
			limite
		);
	}
}
