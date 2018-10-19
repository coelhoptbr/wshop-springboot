package br.com.rodrigo.workshop.limite.broker;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

@Component
public interface BrokerOutput {
	
	String LIMITE_CRIADO = "limiteCriado";
	
	@Output(BrokerOutput.LIMITE_CRIADO)
	MessageChannel limiteCriado();
	
}
