package io.github.vcdl1997.avaliadorcredito.infra.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.github.vcdl1997.avaliadorcredito.application.representation.response.CartaoClienteResponse;
import io.github.vcdl1997.avaliadorcredito.application.representation.response.CartaoResponse;

@FeignClient(value = "cartoes", path="/cartoes")
public interface CartoesResourceClient {
	
	@GetMapping(params = "cpf")
	public ResponseEntity<List<CartaoClienteResponse>> getCartoesPorCpf(@RequestParam("cpf") String cpf);
	
	@GetMapping(params = "renda")
	public ResponseEntity<List<CartaoResponse>> getCartoesRendaMenorIgual(@RequestParam("renda") Long renda);
	
}
