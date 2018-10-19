package br.com.rodrigo.workshop.contacorrente.config;

import org.springframework.context.annotation.Configuration;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	//para consultar a documentacao desta api
	//http://localhost:3535/swagger-ui.html
}

//http://localhost:8500
//interface do consul (proxy)
//adicionar keyvalue no consul: 
//(prefixo.nomeaplic.nomevar -> minhasconfig.conta-corrente.flgHabilitarSeguranca

//rabbit http://localhost:15672/ usuario e senha: guest