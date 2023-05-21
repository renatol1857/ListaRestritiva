package com.renato.listrest.models.entities;

import java.io.Serializable;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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
public class Padrao implements Serializable {
	@Transient
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private PadroesEn tipoPadrao;

	private StatusEn status;

	@Column(length = 50)
	private String extra = "";

	@Column(length = 200)
	private String descricao = "";

	@Column(updatable = false, nullable = false)
	@CreationTimestamp
	private Instant dh;

	@Column(nullable = false, updatable = true)
	@UpdateTimestamp
	private Instant dhup;

	public Padrao(PadroesEn padrao) {
		this.tipoPadrao = padrao;
		status = StatusEn.APROVACAO;
	}

	public Padrao(PadroesEn padrao, String descricao) {
		this(padrao);
		this.descricao = descricao;
		status = StatusEn.APROVACAO;
	}

	public Padrao(PadroesEn padrao, String descricao, String extra) {
		this(padrao, descricao);
		this.extra = extra;
	}

	public String getDh() {
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").withZone(ZoneId.systemDefault());
		return fmt.format(this.dh);
	}

	public String getDhup() {
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").withZone(ZoneId.systemDefault());
		return fmt.format(this.dhup);
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
		Padrao other = (Padrao) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Padrao [id=" + id + ", padrao=" + tipoPadrao + ", status=" + status + ", extra=" + extra + ", descricao="
				+ descricao + ", dh=" + getDh() + ", dhup=" + getDhup() + "]";
	}

}
