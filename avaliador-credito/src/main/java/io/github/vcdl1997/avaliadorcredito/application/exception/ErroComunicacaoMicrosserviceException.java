package io.github.vcdl1997.avaliadorcredito.application.exception;

import lombok.Getter;

public class ErroComunicacaoMicrosserviceException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	@Getter
	private Integer status;

	public ErroComunicacaoMicrosserviceException(String msg, Integer status) {
		super(msg);
		this.status = status;
	}
}
