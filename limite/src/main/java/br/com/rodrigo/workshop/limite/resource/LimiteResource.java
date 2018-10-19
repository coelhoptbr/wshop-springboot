package br.com.rodrigo.workshop.limite.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rodrigo.workshop.limite.model.Limite;
import br.com.rodrigo.workshop.limite.service.LimiteService;

@RestController
@RequestMapping("/cc/limite")
public class LimiteResource {

	public final LimiteService serv;

	public LimiteResource(LimiteService serv) {
		super();
		this.serv = serv;
	}
	
	@PostMapping
	public ResponseEntity<Limite> inserir(@Valid @RequestBody Limite limite) {
		return ResponseEntity.ok(serv.inserir(limite));	
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> excluir(@PathVariable String id) {
		serv.excluir(id);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Limite> alterar(@PathVariable String id, @Valid @RequestBody Limite limite) {
		return ResponseEntity.ok(serv.alterar(id, limite));
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Limite> buscar(@PathVariable String id) {
		/* Java8 
		return serv.buscar(id)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity
				.notFound().build());
		*/
				
		if (serv.buscar(id).isPresent()) {
			return ResponseEntity.ok(serv.buscar(id).get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping
	public ResponseEntity<List<Limite>> buscarTodos() {
		return ResponseEntity.ok(serv.buscarTodos());
	}
	
	@GetMapping(path="/p")
	public ResponseEntity<Page<Limite>> buscarTodosPaginacao(Pageable pageable) {
		return ResponseEntity.ok(serv.buscarTodosPaginacao(pageable));
		/*
			localhost:3535/cc/limite/p?size=2&page=1&sort=id
			
			size - qtd registros por pagina
			page - exibe somente a pagina escolhida (1 = segunda pagina)
			sort - ordenacao dos elementos a partir de um atributo
		
		*/
	}
	
	@GetMapping(path="/cc/{idConta}")
	public ResponseEntity<Page<Limite>> buscarPorTipoLimitePaginacao(
			@PathVariable("idConta") String idConta, 
			Pageable pageable) {
		return ResponseEntity.ok(serv.buscarPorConta(idConta, pageable));
		/* localhost:3535/cc/limite/cc/ksuhiwht874y837hr/?size=2&page=1&sort=id */
	}
	
}
