package io.github.vcdl1997.clientes.application;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.github.vcdl1997.clientes.application.exception.ClienteNaoEncontradoException;
import io.github.vcdl1997.clientes.application.representation.ClienteRequest;
import io.github.vcdl1997.clientes.domain.Cliente;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
@Slf4j
public class ClienteResource {
	
	private final ClienteService clienteService;
	
	@GetMapping
	public String status(){
		log.info("obtendo o status do serviço de clientes");
		return "ok";
	}

	@GetMapping("/{cpf}")
	public ResponseEntity<Cliente> dadosCliente(@PathVariable String cpf){
		try {
			Cliente cliente = clienteService.findByCpf(cpf);
			return ResponseEntity.ok().body(cliente);
		}catch(ClienteNaoEncontradoException e) {
			return ResponseEntity.notFound().build();
		}	
	}
	
	@PostMapping
	public ResponseEntity salvar(@RequestBody ClienteRequest request){
		Cliente cliente = request.toModel();
		clienteService.save(cliente);
		
		URI headerLocation = ServletUriComponentsBuilder
			.fromCurrentRequest().path("/{cpf}")
			.buildAndExpand(cliente.getCpf())
			.toUri()
		;
		
		return ResponseEntity.created(headerLocation).build();
	}
	
}
