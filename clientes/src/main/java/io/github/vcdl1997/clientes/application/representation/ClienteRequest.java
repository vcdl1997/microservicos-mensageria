package io.github.vcdl1997.clientes.application.representation;

import io.github.vcdl1997.clientes.domain.Cliente;
import lombok.Data;

@Data
public class ClienteRequest {

	private String cpf;
	private String nome;
	private Integer idade;
	
	public Cliente toModel() {
		return new Cliente(cpf, nome, idade);
	}
}
