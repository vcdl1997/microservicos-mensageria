package io.github.vcdl1997.avaliadorcredito.infra.mqueue;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.vcdl1997.avaliadorcredito.application.representation.request.DadosSolicitacaoEmissaoCartaoRequest;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SolicitacaoEmissaoCartaoPublisher {

	private final RabbitTemplate rabbitTemplate;
	
	@Value("${mq.queues.emissao-cartoes}")
	private String queueEmissaoCartoes;
		
	public void solicitarCartao(DadosSolicitacaoEmissaoCartaoRequest dados) throws JsonProcessingException {
		var json = this.convertIntoJson(dados);
		rabbitTemplate.convertAndSend(queueEmissaoCartoes, json);
	}
	
	public String convertIntoJson(DadosSolicitacaoEmissaoCartaoRequest dados) throws JsonProcessingException {
		ObjectMapper ow = new ObjectMapper();
		var json = ow.writeValueAsString(dados);
		return json;
	}
}
