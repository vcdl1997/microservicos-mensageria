package io.github.vcdl1997.avaliadorcredito.application.representation.request;

import lombok.Data;

@Data
public class DadosAvaliacaoRequest {
	private String cpf;
	private Long renda;
}
