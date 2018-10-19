package br.com.rodrigo.workshop.limite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient 
public class LimiteApplication 
{
    public static void main( String[] args )
    {
		SpringApplication.run(LimiteApplication.class, args);
    }
}
