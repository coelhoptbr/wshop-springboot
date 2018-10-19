package br.com.rodrigo.workshop.limite.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import br.com.rodrigo.workshop.contacorrente.model.Conta;
import br.com.rodrigo.workshop.limite.broker.BrokerInput;
import br.com.rodrigo.workshop.limite.model.Limite;
import br.com.rodrigo.workshop.limite.repo.LimiteRepo;

@Service
public class LimiteService {

	private final LimiteRepo repo;
	private final BrokerInput brokerInput;

	public LimiteService(LimiteRepo repo, BrokerInput brokerInput) {
		super();
		this.repo = repo;
		this.brokerInput = brokerInput;
	}
	
	public List<Limite> buscarTodos() {
		return repo.findAll();
	}
	
	public Page<Limite> buscarTodosPaginacao(Pageable pageable) {
		return repo.findAll(pageable);
	}
	
	@StreamListener(target=BrokerInput.CONTA_CORRENTE_CRIADA)
	public void contaCorrenteCriada(Conta conta) {
		final Limite limite = new Limite();
		limite.setIdConta(conta.getId());
		limite.setValor(BigDecimal.ONE);
		repo.save(limite);
	}
	
	public Limite inserir(Limite limite) {
		final Limite novoLimite = repo.save(limite);
		
		//brokerOutput.limiteCriado().send(MessageBuilder.withPayload(novoLimite).build());
		
		return novoLimite;
	}
	
	public Optional<Limite> buscar(String id) {
		return Optional.ofNullable(repo.findOne(id));
	}
	
	public Page<Limite> buscarPorConta(String idConta, Pageable pageable) {
		return repo.findByIdConta(idConta, pageable);
	}
	
	public Limite alterar(String id, Limite limite) {
		Optional<Limite> limiteExistente = buscar(id);
		if (limiteExistente.isPresent()) {
			limite.setId(id);
			return repo.save(limite);
		} else {
			throw new IllegalArgumentException("Limite não encontrado.");
		}
		
	}
	
	public void excluir(String id) {
		repo.delete(id);
	}
}
