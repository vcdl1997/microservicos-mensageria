package io.github.vcdl1997.clientes.application.exception;

public class ClienteNaoEncontradoException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ClienteNaoEncontradoException() {
		super("Nenhum Cliente foi encontrado com base no CPF informado");
	}
}
