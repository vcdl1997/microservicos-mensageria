package io.github.vcdl1997.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}
	
	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder) {
		return builder
			.routes()
			.route(r -> r.path("/clientes/**").uri("lb://clientes"))
			.route(r -> r.path("/cartoes/**").uri("lb://cartoes"))
			.route(r -> r.path("/avaliacoes-credito/**").uri("lb://avaliadorcredito"))
			.build()
		;
	}

}
