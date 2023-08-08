package io.github.vcdl1997.cartoes.application;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import io.github.vcdl1997.cartoes.domain.Cartao;
import io.github.vcdl1997.cartoes.infra.repository.CartaoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartaoService {

	private final CartaoRepository cartaoRepository;
	
	@Transactional
	public Cartao save(Cartao cartao) {
		return cartaoRepository.save(cartao);
	}
	
	public List<Cartao> getCartoesRendaMenorIgual(Long renda) {
		
		var rendaBigDecimail = BigDecimal.valueOf(renda);
		
		return cartaoRepository.findByRendaLessThanEqual(rendaBigDecimail);
	}
	
}
