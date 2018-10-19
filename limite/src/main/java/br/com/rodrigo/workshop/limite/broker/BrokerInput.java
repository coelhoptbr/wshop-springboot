package br.com.rodrigo.workshop.limite.broker;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

@Component
public interface BrokerInput {

	String CONTA_CORRENTE_CRIADA = "contaCorrenteCriada";
	
	@Input(BrokerInput.CONTA_CORRENTE_CRIADA)
	SubscribableChannel contaCorrenteCriada();
	
}
