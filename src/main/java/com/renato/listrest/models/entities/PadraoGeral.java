package com.renato.listrest.models.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.renato.listrest.models.enums.PadroesEn;
import com.renato.listrest.models.enums.StatusEn;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class PadraoGeral implements Serializable {
	@Transient
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private PadroesEn padrao;
	
	private StatusEn status;

	@Column(length = 200)
	private String extra="";	

	@Column(length = 200)
	private String descricao="";	
	
	@Column(updatable = false, nullable = false)
	@CreationTimestamp
	private Instant dh;
	
	@Column(nullable = false, updatable = true)
	@UpdateTimestamp
	private Instant dhup;
	
	public PadraoGeral(PadroesEn padrao, String descricao) {
		this.padrao = padrao;
		this.descricao = descricao;
		status = StatusEn.APROVACAO;
	}

	public PadraoGeral(PadroesEn padrao, String extra, String descricao) {
		this(padrao,descricao);
		this.extra = extra;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PadraoGeral other = (PadraoGeral) obj;
		return Objects.equals(id, other.id);
	}

}
