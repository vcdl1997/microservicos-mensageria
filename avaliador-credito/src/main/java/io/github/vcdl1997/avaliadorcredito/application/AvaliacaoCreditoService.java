package io.github.vcdl1997.avaliadorcredito.application;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import feign.FeignException.FeignClientException;
import io.github.vcdl1997.avaliadorcredito.application.exception.DadosClienteNotFoundException;
import io.github.vcdl1997.avaliadorcredito.application.exception.ErroComunicacaoMicrosserviceException;
import io.github.vcdl1997.avaliadorcredito.application.exception.ErroSolicitacaoCartaoException;
import io.github.vcdl1997.avaliadorcredito.application.representation.request.DadosSolicitacaoEmissaoCartaoRequest;
import io.github.vcdl1997.avaliadorcredito.application.representation.response.CartaoAprovadoResponse;
import io.github.vcdl1997.avaliadorcredito.application.representation.response.CartaoClienteResponse;
import io.github.vcdl1997.avaliadorcredito.application.representation.response.CartaoResponse;
import io.github.vcdl1997.avaliadorcredito.application.representation.response.DadosClienteResponse;
import io.github.vcdl1997.avaliadorcredito.application.representation.response.ProtocoloSolicitacaoCartaoResponse;
import io.github.vcdl1997.avaliadorcredito.application.representation.response.RetornoAvaliacaoClienteResponse;
import io.github.vcdl1997.avaliadorcredito.application.representation.response.SituacaoClienteResponse;
import io.github.vcdl1997.avaliadorcredito.infra.clients.CartoesResourceClient;
import io.github.vcdl1997.avaliadorcredito.infra.clients.ClienteResourceClient;
import io.github.vcdl1997.avaliadorcredito.infra.mqueue.SolicitacaoEmissaoCartaoPublisher;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AvaliacaoCreditoService {
	
	private final ClienteResourceClient clienteResourceClient;
	private final CartoesResourceClient cartoesResourceClient;
	private final SolicitacaoEmissaoCartaoPublisher solicitacaoEmissaoCartaoPublisher;

	public SituacaoClienteResponse obterSituacaoCliente(String cpf) throws DadosClienteNotFoundException {
		
		try {
			ResponseEntity<DadosClienteResponse> dadosClienteResponse = clienteResourceClient.dadosCliente(cpf);
			ResponseEntity<List<CartaoClienteResponse>> cartoesResponse = cartoesResourceClient.getCartoesPorCpf(cpf);
			
			return SituacaoClienteResponse
				.builder()
				.cliente(dadosClienteResponse.getBody())
				.cartoes(cartoesResponse.getBody())
				.build()
			;
			
		}catch(FeignClientException e) {
			int status = e.status();
			
			if(HttpStatus.SC_NOT_FOUND == status) {
				throw new DadosClienteNotFoundException();
				
			}
			
			throw new ErroComunicacaoMicrosserviceException(e.getMessage(), status);
		}
	}
	
	
	public RetornoAvaliacaoClienteResponse realizarAvaliacaoCliente(String cpf, Long renda){
		try {
			
			ResponseEntity<DadosClienteResponse> dadosClienteResponse = clienteResourceClient.dadosCliente(cpf);
			ResponseEntity<List<CartaoResponse>> cartoesResponse = cartoesResourceClient.getCartoesRendaMenorIgual(renda);
			
			List<CartaoAprovadoResponse> cartoesAprovadoss = cartoesResponse
				.getBody()
				.stream()
				.map(c -> {
					DadosClienteResponse dadosCliente = dadosClienteResponse.getBody();
					BigDecimal limiteBasico = c.getLimiteBasico();
					BigDecimal rendaBD = BigDecimal.valueOf(renda);
					BigDecimal idadeDB = BigDecimal.valueOf(dadosCliente.getIdade());
					var fator = idadeDB.divide(BigDecimal.valueOf(10));
					BigDecimal limiteAprovado = fator.multiply(limiteBasico);
					
					CartaoAprovadoResponse aprovado = new CartaoAprovadoResponse();
					aprovado.setCartao(c.getNome());
					aprovado.setBandeira(c.getBandeira());
					aprovado.setLimiteAprovado(limiteAprovado);
					return aprovado;
				})
				.collect(Collectors.toList())
			;
			
			return new RetornoAvaliacaoClienteResponse(cartoesAprovadoss);
		
		}catch(FeignClientException e) {
			int status = e.status();
			
			if(HttpStatus.SC_NOT_FOUND == status) {
				throw new DadosClienteNotFoundException();
				
			}
			
			throw new ErroComunicacaoMicrosserviceException(e.getMessage(), status);
		}
	}
	
	public ProtocoloSolicitacaoCartaoResponse solicitarEmissaoCartao(DadosSolicitacaoEmissaoCartaoRequest request) {
		try {
			solicitacaoEmissaoCartaoPublisher.solicitarCartao(request);
			var protocolo = UUID.randomUUID().toString();
			return new ProtocoloSolicitacaoCartaoResponse(protocolo);
		}catch(Exception e) {
			throw new ErroSolicitacaoCartaoException(e.getMessage());
		}
	}
}
