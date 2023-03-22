package com.api.Internetbanking.dtos;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ClienteDto {
	
	@NotBlank
	private String nome;
	
	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dataNascimento;
	
	private ContaDto conta;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public ContaDto getConta() {
		return conta;
	}

	public void setConta(ContaDto conta) {
		this.conta = conta;
	}
	
}
