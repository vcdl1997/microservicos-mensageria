package io.github.vcdl1997.cartoes.domain;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Cartao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	@Enumerated(EnumType.STRING)
	private BandeiraCartao bandeira;
	
	private BigDecimal renda;
	
	private BigDecimal limiteBasico;
	
	@OneToMany(mappedBy = "cartao")
	@JsonIgnore
	private Set<ClienteCartao> cartoesPorCliente = new HashSet<>();

	public Cartao(
		String nome, 
		BandeiraCartao bandeira, 
		BigDecimal renda, 
		BigDecimal limiteBasico
	) {
		this.nome = nome;
		this.bandeira = bandeira;
		this.renda = renda;
		this.limiteBasico = limiteBasico;
	}	
	
	public Set<ClienteCartao> getCartoesPorCliente(){
		return this.cartoesPorCliente;
	}
}
