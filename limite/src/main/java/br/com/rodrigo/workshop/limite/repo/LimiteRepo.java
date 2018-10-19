package br.com.rodrigo.workshop.limite.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.rodrigo.workshop.limite.model.Limite;

@Repository
public interface LimiteRepo extends MongoRepository<Limite, String> {

	Page<Limite> findByIdConta(String idConta, Pageable pageable);
	
}
