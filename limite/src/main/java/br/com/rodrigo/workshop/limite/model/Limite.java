package br.com.rodrigo.workshop.limite.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="limite")
public class Limite implements Serializable {

private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	
	@NotNull
	private String idConta;
	
	@NotNull
	private BigDecimal valor;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdConta() {
		return idConta;
	}

	public void setIdConta(String idConta) {
		this.idConta = idConta;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	
	
}
