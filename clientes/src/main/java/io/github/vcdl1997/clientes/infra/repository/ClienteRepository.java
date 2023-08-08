package io.github.vcdl1997.clientes.infra.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.vcdl1997.clientes.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

	public Optional<Cliente> findByCpf(String cpf);
	
}
