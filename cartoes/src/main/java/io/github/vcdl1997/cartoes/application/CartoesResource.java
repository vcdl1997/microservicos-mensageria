package io.github.vcdl1997.cartoes.application;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.vcdl1997.cartoes.application.representation.request.CartaoRequest;
import io.github.vcdl1997.cartoes.application.representation.response.CartoesPorClienteResponse;
import io.github.vcdl1997.cartoes.domain.Cartao;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cartoes")
@RequiredArgsConstructor
public class CartoesResource {
	
	private final CartaoService cartaoService;
	private final ClienteCartaoService clienteCartaoService;

	@GetMapping
	public String status() {
		return "ok";
	}
	
	@PostMapping
	public ResponseEntity save(@RequestBody CartaoRequest request){
		Cartao cartao = request.fromModel();
		
		cartaoService.save(cartao);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@GetMapping(params = "renda")
	public ResponseEntity<List<Cartao>> getCartoesRendaMenorIgual(@RequestParam("renda") Long renda){
		System.out.println(renda.toString());
		
		List<Cartao> list = cartaoService.getCartoesRendaMenorIgual(renda);
		
		return ResponseEntity.ok(list);
	}
	
	@GetMapping(params = "cpf")
	public ResponseEntity<List<CartoesPorClienteResponse>> getCartoesPorCpf(@RequestParam("cpf") String cpf){
		
		List<CartoesPorClienteResponse> list = CartoesPorClienteResponse.fromList(clienteCartaoService.getCartoesPorCpf(cpf));
		
		return ResponseEntity.ok(list);
	}
}
