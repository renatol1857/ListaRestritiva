package com.renato.listrest.models.enums;

import java.util.Optional;

public enum ServicoEn {
	MOD_CONF(1, "End Points para gerenciar as configurações do Sistema."),
	MOD_FREE(2, "End Points para gerenciar a lista estática de telefone livre para acesso ao Sistema."),
	MOD_RESTITIVA(3, "End Points para gerenciar a lista estática de telefone restritivos para acesso ao Sistema."),
	MOD_PATTERN(4, "End Points para gerenciar os padrões estáticos de telefone para acesso ao Sistema."),
	MOD_DIN_FREE(5, "End Points utilizados pela lista dinâmica de telefone livre para acesso ao Sistema."),
	MOD_DIN_RESTITIVA(6, "End Points utilizados pela lista dinâmica de telefone restritivos para acesso ao Sistema."),
	MOD_DIN_PATTERN(7, "End Points utilizados pela lista dinâmica de telefone para acesso ao Sistema."),
	MOD_MONITOR(8, "End Points utilizados para monitorar o sistema."),
	EXEC_EXPURGO(9, "Módulo necessário para executar a rotina de expurgo."),
	LISTA_ESTATICA(10, "End Points utilizados na Listas Restritivas Estática."),
	LISTA_DINAMICA(11, "End Points utilizados na Listas Restritivas Dinâmica."),
	LISTA_MISTA(12, "End Points utilizados na Listas Restritivas Estática e Dinâmica.");

	private int cod;
	private String msg;

	ServicoEn(int cod, String msg) {
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
		for (ServicoEn x : ServicoEn.values()) {
			if (x.getCod() == cod)
				return true;
		}
		return false;
	}

	public static Optional<ServicoEn> toEnum(Integer cod) {
		if (cod == null)
			return null;
		for (ServicoEn x : ServicoEn.values()) {
			if (x.getCod() == cod)
				return Optional.of(x);
		}
		return null;
	}

	public static Optional<String> toMsg(Integer cod) {
		if (cod == null)
			return null;
		for (ServicoEn x : ServicoEn.values()) {
			if (x.getCod() == cod)
				return Optional.of(x.getMsg());
		}
		return null;
	}

}
