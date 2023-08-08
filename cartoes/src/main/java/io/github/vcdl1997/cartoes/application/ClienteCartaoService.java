package io.github.vcdl1997.cartoes.application;

import java.util.List;

import org.springframework.stereotype.Service;

import io.github.vcdl1997.cartoes.domain.ClienteCartao;
import io.github.vcdl1997.cartoes.infra.repository.ClienteCartaoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteCartaoService {

	private final ClienteCartaoRepository clienteCartaoRepository;
	
	public List<ClienteCartao> getCartoesPorCpf(String cpf){
		return clienteCartaoRepository.findByCpf(cpf);
	}
	
}
