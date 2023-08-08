package io.github.vcdl1997.cartoes.application.representation.response;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import io.github.vcdl1997.cartoes.domain.ClienteCartao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartoesPorClienteResponse {

	private String nome;
	private String bandeira;
	private BigDecimal limiteLiberado;
	
	
	public static CartoesPorClienteResponse fromModel(ClienteCartao model){
		return new CartoesPorClienteResponse(
			model.getCartao().getNome(), 
			model.getCartao().getBandeira().toString(), 
			model.getLimite()
		);
	}
	
	public static List<CartoesPorClienteResponse> fromList(List<ClienteCartao> list){
		return list.stream().map(x -> fromModel(x)).collect(Collectors.toList());
	}
}
