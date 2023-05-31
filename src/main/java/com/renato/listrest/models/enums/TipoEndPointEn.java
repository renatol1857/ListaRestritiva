package com.renato.listrest.models.enums;

import java.util.Optional;

public enum TipoEndPointEn {
	CONSULTAR(1, "Consultar um elemento."), 
	CONSULTAR_INC(2, "Consultar um elemento e add historico."),
	CONSULTAR_LIKE(3, "Consultar um elemento com parte da informacao."),
	CONSULTAR_COM_HISTORICO(4, "Consultar um elemento com parte da informacao."),
	LISTAR_ALL(4, "Consultar um elemento com parte da informacao."),
	INCLUIR(4, "Consultar um elemento com parte da informacao."),
	ATUALIZAR(4, "Consultar um elemento com parte da informacao."),
	ATUALIZAR_PARAM(4, "Consultar um elemento com parte da informacao."),
	APAGAR(4, "Consultar um elemento com parte da informacao."),
	CARGA(4, "Consultar um elemento com parte da informacao."),
	EXPORTAR(4, "Consultar um elemento com parte da informacao."),
	ADD_MONITOR(4, "Consultar um elemento com parte da informacao."),
	DEL_MONITOR(4, "Consultar um elemento com parte da informacao.");

	private int cod;
	private String msg;

	TipoEndPointEn(int cod, String msg) {
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
		for (TipoEndPointEn x : TipoEndPointEn.values()) {
			if (x.getCod() == cod)
				return true;
		}
		return false;
	}

	public static Optional<TipoEndPointEn> toEnum(Integer cod) {
		if (cod == null)
			return null;
		for (TipoEndPointEn x : TipoEndPointEn.values()) {
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
