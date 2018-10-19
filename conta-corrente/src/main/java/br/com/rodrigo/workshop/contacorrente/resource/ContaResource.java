package br.com.rodrigo.workshop.contacorrente.resource;

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

import br.com.rodrigo.workshop.contacorrente.enums.TipoContaEnum;
import br.com.rodrigo.workshop.contacorrente.model.Conta;
import br.com.rodrigo.workshop.contacorrente.service.ContaService;

@RestController
@RequestMapping("/cc/conta")
public class ContaResource {
	public final ContaService serv;
	
	public ContaResource(ContaService serv) {
		super();
		this.serv = serv;
	}
	
	@PostMapping
	public ResponseEntity<Conta> inserir(@Valid @RequestBody Conta conta) {
		return ResponseEntity.ok(serv.inserir(conta));	
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> excluir(@PathVariable String id) {
		serv.excluir(id);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Conta> atualizar(@PathVariable String id, @Valid @RequestBody Conta conta) {
		return ResponseEntity.ok(serv.atualizar(id, conta));
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Conta> buscar(@PathVariable String id) {
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
	public ResponseEntity<List<Conta>> buscarTodos() {
		return ResponseEntity.ok(serv.buscarTodos());
	}
	
	@GetMapping(path="/p")
	public ResponseEntity<Page<Conta>> buscarTodosPaginacao(Pageable pageable) {
		return ResponseEntity.ok(serv.buscarTodosPaginacao(pageable));
		/*
			localhost:3535/cc/conta/p?size=2&page=1&sort=id
			
			size - qtd registros por pagina
			page - exibe somente a pagina escolhida (1 = segunda pagina)
			sort - ordenacao dos elementos a partir de um atributo
		
		*/
	}
	
	@GetMapping(path="/tp/{tipoConta}")
	public ResponseEntity<Page<Conta>> buscarPorTipoContaPaginacao(
			@PathVariable("tipoConta") TipoContaEnum tipoConta, 
			Pageable pageable) {
		return ResponseEntity.ok(serv.buscarPorTipoContaPaginacao(tipoConta, pageable));
		/* localhost:3535/cc/conta/tp/INVESTIMENTO/?size=2&page=1&sort=id */
	}
}
