package io.github.vcdl1997.cartoes.infra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.vcdl1997.cartoes.domain.ClienteCartao;

public interface ClienteCartaoRepository extends JpaRepository<ClienteCartao, Long>{

	List<ClienteCartao> findByCpf(String cpf);
	
}
