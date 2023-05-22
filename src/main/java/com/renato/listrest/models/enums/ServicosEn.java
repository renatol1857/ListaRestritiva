package com.renato.listrest.models.enums;

import java.util.Optional;

public enum ServicosEn {
	LISTA_FREE(1, "Lista estática de telefone livre para acesso os Sistemas, geralmente para testes"),
	LISTA_RESTITIVA(2, "Lista estática de telefone que NÃO podem acessar os Sistemas"),
	LISTA_PATTERN(3, "Lista estática de padrões telefônicos que NÃO podem acessar os Sistemas"),
	LISTA_RESTRITIVA(4, "Conjunto de métodos para Lista Restritiva Estática"),
	LISTA_RESTRITIVA_DINAMICA(5, "Conjunto de métodos para Lista Restritiva Dinâmica");

	private int cod;
	private String msg;

	ServicosEn(int cod, String msg) {
		this.cod = cod;
		this.msg = msg;
	}

	public int getCod() {
		return cod;
	}

	public String getMsg() {
		return msg;
	}

	public static boolean isValid(Integer cod) {
		if (cod == null)
			return false;
		for (ServicosEn x : ServicosEn.values()) {
			if (x.getCod() == cod)
				return true;
		}
		return false;
	}

	public static Optional<ServicosEn> toEnum(Integer cod) {
		if (cod == null)
			return null;
		for (ServicosEn x : ServicosEn.values()) {
			if (x.getCod() == cod)
				return Optional.of(x);
		}
		return null;
	}

	public static Optional<String> toMsg(Integer cod) {
		if (cod == null)
			return null;
		for (ServicosEn x : ServicosEn.values()) {
			if (x.getCod() == cod)
				return Optional.of(x.getMsg());
		}
		return null;
	}

}
