package io.github.vcdl1997.avaliadorcredito.application.exception;

public class DadosClienteNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public DadosClienteNotFoundException() {
		super("Dados do Cliente não foram encontrados para o CPF informado");
	}
}
