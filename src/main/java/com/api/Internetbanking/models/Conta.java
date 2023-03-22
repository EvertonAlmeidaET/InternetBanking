package com.api.Internetbanking.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.api.Internetbanking.models.Enum.TipoTransacaoEnum;

@Entity
@Table(name = "tb_conta")
public class Conta implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private BigDecimal saldo = BigDecimal.ZERO;
	
	private String numeroConta = "";
	
	private Boolean exclusive = Boolean.FALSE;
	
	@OneToMany(cascade = CascadeType.ALL , orphanRemoval = true)
	@JoinColumn(name = "id_conta")
	private List<Extrato> extrato = new ArrayList<>();
	
	public Conta() {
	}
	
	
	public void sacar(BigDecimal valor) {
		
		if(valor.compareTo(BigDecimal.ZERO) == 0 || this.saldo.compareTo(BigDecimal.ZERO) == 0) {
			throw new IllegalArgumentException("Impossivel sacar valor zerado!!");
			
		} else {
			
			if(valor.compareTo(new BigDecimal("100")) <= 0 || this.exclusive.equals(Boolean.TRUE)){
				sacarValor(valor);
			} else if(valor.compareTo(new BigDecimal("100")) == 1 && valor.compareTo(new BigDecimal("300")) == -1){
				valor = valor.add(valor.multiply(new BigDecimal("0.4").divide(new BigDecimal("100"))));
				sacarValor(valor);
			} else {
				valor = valor.add(valor.multiply(new BigDecimal("1").divide(new BigDecimal("100"))));
				sacarValor(valor);
			}
			
		}
		
	}
	
	private void sacarValor(BigDecimal valor) {
		this.saldo = this.saldo.subtract(valor);
		Extrato extrato = new Extrato();
		extrato.gerarExtrato(TipoTransacaoEnum.SAQUE, new Date(), valor);
		getExtrato().add(extrato);
	}
	
	public void depositar(BigDecimal valor) {
		
		if(valor.compareTo(BigDecimal.ZERO) <= 0) {
			throw new IllegalArgumentException("Por favor informar um valor valido para deposito!!");
		} else {
			this.saldo = this.saldo.add(valor);
			Extrato extrato = new Extrato();
			extrato.gerarExtrato(TipoTransacaoEnum.DEPOSITO, new Date(), valor);
			getExtrato().add(extrato);
		}
		
		
	}
	
	public void transferir(BigDecimal valor, Conta destino) {
		
		if (this.saldo.compareTo(valor) > 0) {
			sacar(valor);
			destino.depositar(valor);
			Extrato extrato = new Extrato();
			extrato.gerarExtrato(TipoTransacaoEnum.TRANSFERENCIA, new Date(), valor);
			getExtrato().add(extrato);
			
		}
		
	}
	
	public List<Extrato> getExtrato() {
		return extrato;
	}
	

	public String getNumeroConta() {
		return numeroConta;
	}


	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}


	public Boolean getExclusive() {
		return exclusive;
	}


	public void setExclusive(Boolean exclusive) {
		this.exclusive = exclusive;
	}


	public BigDecimal getSaldo() {
		return saldo.setScale(2, BigDecimal.ROUND_HALF_DOWN);
	}
	
	
	public Long getId() {
		return id;
	}

}
