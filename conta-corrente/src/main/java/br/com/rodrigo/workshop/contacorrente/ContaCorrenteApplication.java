package br.com.rodrigo.workshop.contacorrente;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient //para o consul
public class ContaCorrenteApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContaCorrenteApplication.class, args);
	}
}
