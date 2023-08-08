package io.github.vcdl1997.avaliadorcredito.application;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.vcdl1997.avaliadorcredito.application.exception.DadosClienteNotFoundException;
import io.github.vcdl1997.avaliadorcredito.application.exception.ErroComunicacaoMicrosserviceException;
import io.github.vcdl1997.avaliadorcredito.application.exception.ErroSolicitacaoCartaoException;
import io.github.vcdl1997.avaliadorcredito.application.representation.request.DadosAvaliacaoRequest;
import io.github.vcdl1997.avaliadorcredito.application.representation.request.DadosSolicitacaoEmissaoCartaoRequest;
import io.github.vcdl1997.avaliadorcredito.application.representation.response.ProtocoloSolicitacaoCartaoResponse;
import io.github.vcdl1997.avaliadorcredito.application.representation.response.RetornoAvaliacaoClienteResponse;
import io.github.vcdl1997.avaliadorcredito.application.representation.response.SituacaoClienteResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("avaliacoes-credito")
@RequiredArgsConstructor
public class AvaliacaoCreditoResource {
	
	private final AvaliacaoCreditoService avaliacaoCreditoClienteService;

	@GetMapping
	public String status() {
		return "ok";
	}
	
	@GetMapping(value="situacao-cliente", params = "cpf")
	public ResponseEntity consultaSituacaoCliente(@RequestParam("cpf") String cpf){
		try {
			SituacaoClienteResponse situacao = avaliacaoCreditoClienteService.obterSituacaoCliente(cpf);	
			return ResponseEntity.ok(situacao);
		}catch(DadosClienteNotFoundException e){
			return ResponseEntity.notFound().build();
		}catch(ErroComunicacaoMicrosserviceException e){
			return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
		}		
	}
	
	
	@PostMapping
	public ResponseEntity realizarAvaliacao(@RequestBody DadosAvaliacaoRequest dados) {
		try {
			RetornoAvaliacaoClienteResponse situacao = avaliacaoCreditoClienteService
				.realizarAvaliacaoCliente(dados.getCpf(), dados.getRenda())
			;	
			
			return ResponseEntity.ok(situacao);
		}catch(DadosClienteNotFoundException e){
			return ResponseEntity.notFound().build();
		}catch(ErroComunicacaoMicrosserviceException e){
			return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
		}	
	}
	
	@PostMapping("solicitacoes-cartao")
	public ResponseEntity solicitarCartao(@RequestBody DadosSolicitacaoEmissaoCartaoRequest request) {
		try {
			ProtocoloSolicitacaoCartaoResponse response = avaliacaoCreditoClienteService
				.solicitarEmissaoCartao(request)
			;
			
			return ResponseEntity.ok(response);
		}catch(ErroSolicitacaoCartaoException e) {
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	}
}
