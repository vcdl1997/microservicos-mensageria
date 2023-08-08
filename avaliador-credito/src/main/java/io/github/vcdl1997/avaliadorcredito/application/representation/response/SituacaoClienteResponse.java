package io.github.vcdl1997.avaliadorcredito.application.representation.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SituacaoClienteResponse {

	private DadosClienteResponse cliente;
	private List<CartaoClienteResponse> cartoes;
	
}
