package com.api.Internetbanking.models.Enum;

public enum TipoTransacaoEnum {
	
	TRANSFERENCIA(1, "TRANFERENCIA"),
	SAQUE(2, "SAQUE"),
	DEPOSITO(3, "DEPOSITO"),
	VAZIO(4, "VAZIO");
	
	private Integer codigo;
	private String descricao;
	
	/**
	 * 
	 * @param codigo
	 * @param descricao
	 */
	TipoTransacaoEnum(Integer codigo, String descricao){
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getDescricao() {
		return descricao;
	}
	

	/**
	 * 
	 * @return
	 */
	public Integer getCodigo() {
		return codigo;
	}
	
	
	

}
