package io.github.vcdl1997.avaliadorcredito.infra.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import io.github.vcdl1997.avaliadorcredito.application.representation.response.DadosClienteResponse;

@FeignClient(value = "clientes", path="/clientes")
public interface ClienteResourceClient {

	@GetMapping("/{cpf}")
	public ResponseEntity<DadosClienteResponse> dadosCliente(@PathVariable String cpf);
}
