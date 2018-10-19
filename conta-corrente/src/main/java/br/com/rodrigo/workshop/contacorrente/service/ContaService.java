package br.com.rodrigo.workshop.contacorrente.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import br.com.rodrigo.workshop.contacorrente.broker.BrokerOutput;
import br.com.rodrigo.workshop.contacorrente.enums.TipoContaEnum;
import br.com.rodrigo.workshop.contacorrente.model.Conta;
import br.com.rodrigo.workshop.contacorrente.repo.ContaRepo;

@Service
public class ContaService {

	private final ContaRepo repo;
	private final CpfRemoteService servCpf;
	private final BrokerOutput brokerOutput;

	public ContaService(ContaRepo repo, CpfRemoteService servCpf, BrokerOutput brokerOutput) {
		super();
		this.repo = repo;
		this.servCpf = servCpf;
		this.brokerOutput = brokerOutput;
	}
	
	public Conta inserir(Conta conta) {
		servCpf.encontrarNomePorCpf(conta.getCpf())
			.ifPresent(
					result -> conta.setNome(result.get("name")));
		//busca o nome de outro lugar e atribui ao objeto conta
		final Conta novaConta = repo.save(conta);
		
		brokerOutput.contaCorrenteCriada().send(MessageBuilder.withPayload(novaConta).build());
		
		return novaConta;
	}
	
	public List<Conta> buscarTodos() {
		return repo.findAll();
	}
	
	public Optional<Conta> buscar(String id) {
		return Optional.ofNullable(repo.findOne(id));
	}
	
	public Page<Conta> buscarTodosPaginacao(Pageable pageable) {
		return repo.findAll(pageable);
	}
	
	public Page<Conta> buscarPorTipoContaPaginacao(TipoContaEnum tipoConta, Pageable pageable) {
		return repo.findByTipoConta(tipoConta, pageable);
	}

	public void excluir(String id) {
		repo.delete(id);
	}

	public Conta atualizar(String id, Conta conta) {
		Optional<Conta> contaExiste = buscar(id);
		if (contaExiste.isPresent()) {
			conta.setId(id);
			return repo.save(conta);
		} else {
			throw new IllegalArgumentException("Conta não encontrada.");
		}
	}
}
