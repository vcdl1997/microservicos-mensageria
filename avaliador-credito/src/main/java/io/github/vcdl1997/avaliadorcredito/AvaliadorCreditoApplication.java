package io.github.vcdl1997.avaliadorcredito;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableFeignClients
//@PropertySource("classpath:application.properties")
@EnableRabbit
public class AvaliadorCreditoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AvaliadorCreditoApplication.class, args);
	}

}
