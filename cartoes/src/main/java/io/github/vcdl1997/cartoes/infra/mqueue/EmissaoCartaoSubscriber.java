package io.github.vcdl1997.cartoes.infra.mqueue;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.vcdl1997.cartoes.application.representation.request.DadosSolicitacaoEmissaoCartaoRequest;
import io.github.vcdl1997.cartoes.domain.Cartao;
import io.github.vcdl1997.cartoes.domain.ClienteCartao;
import io.github.vcdl1997.cartoes.infra.repository.CartaoRepository;
import io.github.vcdl1997.cartoes.infra.repository.ClienteCartaoRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EmissaoCartaoSubscriber {
	
	private final CartaoRepository cartaoRepository;
	private final ClienteCartaoRepository clienteCartaoRepository;

	@RabbitListener(queues = "${mq.queues.emissao-cartoes}")
	public void receberSolicitacaoEmissao(@Payload String payload) {
		try {
			var mapper = new ObjectMapper();
			DadosSolicitacaoEmissaoCartaoRequest request = mapper.readValue(payload, DadosSolicitacaoEmissaoCartaoRequest.class);
		
			Cartao cartao = cartaoRepository.findById(request.getIdCartao()).orElseThrow();
			ClienteCartao clienteCartao = new ClienteCartao();
			
			clienteCartao.setCpf(request.getCpf());
			clienteCartao.setCartao(cartao);
			clienteCartao.setEndereco(request.getEndereco());
			clienteCartao.setLimite(request.getLimiteLiberado());
			
			clienteCartaoRepository.save(clienteCartao);
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
