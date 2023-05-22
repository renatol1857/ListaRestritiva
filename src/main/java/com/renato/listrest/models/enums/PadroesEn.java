package com.renato.listrest.models.enums;

import java.util.Optional;

public enum PadroesEn {
	LENGTH_MIN(1, "Tamanho mínimo aceitável"), LENGTH_MAX(2, "Tamanho máximo aceitável"),
	START_WITH(3, "String que começa com ABC..."), END_WITH(4, "String que termina com ABC..."),
	CONTENT(5, "String que contém ABC..."), NOT_MOBILE(6, "Não aceita celular."),
	NOT_LANDLINE(7, "Não aceita telefone fixo."), NOT_DDI(8, "Não aceita o DDI ABC."),
	NOT_DDD(9, "Não aceita o DDD ABC."), NOT_SEQUENTIAL(10, "Não aceita os sequencial XXXX."),
	NOT_ANONYMOUS(11, "Não aceita ANONYMOUS como string."),
	NOT_STRING(12, "Não aceita string na composição do número.");

	private int cod;
	private String msg;

	PadroesEn(int cod, String msg) {
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
		for (PadroesEn x : PadroesEn.values()) {
			if (x.getCod() == cod)
				return true;
		}
		return false;
	}

	public static Optional<PadroesEn> toEnum(Integer cod) {
		if (cod == null)
			return null;
		for (PadroesEn x : PadroesEn.values()) {
			if (x.getCod() == cod)
				return Optional.of(x);
		}
		return null;
	}

	public static Optional<String> toMsg(Integer cod) {
		if (cod == null)
			return null;
		for (PadroesEn x : PadroesEn.values()) {
			if (x.getCod() == cod)
				return Optional.of(x.getMsg());
		}
		return null;
	}
}
