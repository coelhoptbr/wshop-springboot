package br.com.rodrigo.workshop.contacorrente.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cc")
public class OlaMundoResource {
	
	@GetMapping
	public ResponseEntity<String> dizerOla() {
		return ResponseEntity.ok("Olá, mundo cruel.");
	}
}
