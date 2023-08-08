package io.github.vcdl1997.clientes.application;

import java.util.Optional;

import org.springframework.stereotype.Service;

import io.github.vcdl1997.clientes.application.exception.ClienteNaoEncontradoException;
import io.github.vcdl1997.clientes.domain.Cliente;
import io.github.vcdl1997.clientes.infra.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteService {

	private final ClienteRepository clienteRepository;
	
	@Transactional
	public Cliente save(Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	public Cliente findByCpf(String cpf) throws ClienteNaoEncontradoException
	{
		Optional<Cliente> busca = clienteRepository.findByCpf(cpf);
		
		if(busca.isEmpty()) throw new ClienteNaoEncontradoException();
		
		return busca.get();
	}

}
