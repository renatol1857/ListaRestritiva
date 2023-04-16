package com.renato.listrest.models.enums;

public enum StatusEn {
	ATIVO(1,"Ativo"),
	INATIVO(2,"Inativo"),
	APROVACAO(3,"Esperando aprovação");
	
	private int cod;
	private String descricao;
	
	private StatusEn (int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return cod;
		}

	public String getDescricao() {
		return descricao;
		}
	
	public static StatusEn toEnum (Integer cod) {
		if (cod == null)
			return null;	
		for (StatusEn x : StatusEn.values()) {
			if (x.getCod() == cod)
				return x;
			}
		throw new IllegalArgumentException("cod inválido [" + cod + "]");	
		}

}
