package com.api.Internetbanking.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.api.Internetbanking.models.Enum.TipoTransacaoEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

@Entity
@Table(name = "tb_extrato")
public class Extrato implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Date dataExtrato;
	
	private String descricaoExtrato;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "id_conta")
	private Conta conta;
	
	public void gerarExtrato(TipoTransacaoEnum tipoTransacao, Date date, BigDecimal valores) {
		
		this.dataExtrato = date;
		this.descricaoExtrato = String.format("valor %s: %.2f - %s", tipoTransacao.getDescricao().toLowerCase(), valores, DateFormat.getDateInstance().format(date));
		
	}
	
	public String extratoDaConta() {
		
		return null;
	}
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	public Date getDataExtrato() {
		return dataExtrato;
	}
	
	public String getDescricaoExtrato() {
		return descricaoExtrato;
	}
	
	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}
	
	

}
