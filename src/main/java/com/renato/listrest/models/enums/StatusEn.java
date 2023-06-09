package com.renato.listrest.models.enums;

import java.util.Optional;

public enum StatusEn {
	ATIVO(1, "O Objeto está Ativo."), INATIVO(2, "O Objeto está Inativo."),
	APROVACAO(3, "O Objeto está para Aprovação.");

	private int cod;
	private String msg;

	StatusEn(int cod, String msg) {
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
		for (StatusEn x : StatusEn.values()) {
			if (x.getCod() == cod)
				return true;
		}
		return false;
	}

	public static Optional<StatusEn> toEnum(Integer cod) {
		if (cod == null)
			return null;
		for (StatusEn x : StatusEn.values()) {
			if (x.getCod() == cod)
				return Optional.of(x);
		}
		return null;
	}

	public static Optional<String> toMsg(Integer cod) {
		if (cod == null)
			return null;
		for (StatusEn x : StatusEn.values()) {
			if (x.getCod() == cod)
				return Optional.of(x.getMsg());
		}
		return null;
	}

}
