package br.com.rodrigo.workshop.contacorrente.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.rodrigo.workshop.contacorrente.enums.TipoContaEnum;
import br.com.rodrigo.workshop.contacorrente.model.Conta;

@Repository
public interface ContaRepo extends MongoRepository<Conta, String> {
	
	Page<Conta> findByTipoConta(TipoContaEnum tipoConta, Pageable pageable);
}
