package br.com.southsystem.workshop.demo.rest;

import br.com.southsystem.workshop.demo.service.CpfService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/cpf")
public class CpfRest {
    private final CpfService cpfService;

    public CpfRest(CpfService cpfService) {
        this.cpfService = cpfService;
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Map<String, String>> findAll(@PathVariable("cpf") String cpf) {
        return cpfService.findByCpf(cpf)
            .map(cpfResult -> ResponseEntity.ok(cpfResult))
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
