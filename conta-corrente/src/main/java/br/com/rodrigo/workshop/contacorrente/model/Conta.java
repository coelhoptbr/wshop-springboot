package br.com.rodrigo.workshop.contacorrente.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.rodrigo.workshop.contacorrente.enums.TipoContaEnum;

@Document(collection="conta")
public class Conta implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	
	@NotNull
	private String numeroConta;
	@NotNull
	private String agencia;
	@NotNull
	private TipoContaEnum tipoConta;
	@NotNull
	private String cpf;
	
	private String nome;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNumeroConta() {
		return numeroConta;
	}
	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}
	public String getAgencia() {
		return agencia;
	}
	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}
	public TipoContaEnum getTipoConta() {
		return tipoConta;
	}
	public void setTipoConta(TipoContaEnum tipoConta) {
		this.tipoConta = tipoConta;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
