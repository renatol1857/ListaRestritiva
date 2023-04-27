package com.renato.listrest.models.dto;

import org.hibernate.validator.constraints.Length;

import com.renato.listrest.models.entities.DNIS;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DNISDTO {
	@NotBlank(message = "O campo 'dnis' não pode estar em branco")
	@NotNull(message = "O campo 'dnis' não pode ser nulo")
	@Length(min = 4, max = 30, message = "O campo 'dnis' deve ter um tamanho entre 4 e 30 bytes")
	protected String dnis; 
	@Length(max = 50, message = "O campo 'alias' deve ter um tamanho max de 50 bytes")
	protected String alias; 
	@Length(max = 50, message = "O campo 'alias' deve ter um tamanho max de 200 bytes")
	protected String descricao;
	
	public DNIS transformaToObj() {
		return new DNIS(dnis, alias, descricao);
	}
}
