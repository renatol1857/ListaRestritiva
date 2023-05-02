package com.renato.listrest.models.entities;

import java.io.Serializable;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
public class DNIS implements Serializable {
	@Transient
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 30, nullable = false, unique = true, updatable = false)
	private String dnis;

	@Column(length = 50)
	private String alias;

	private StatusEn status;

	@Column(length = 200)
	private String descricao;

	@Column(nullable = false, updatable = false)
	@CreationTimestamp
	private  Instant dh;

	@Column(nullable = false, updatable = true)
	@UpdateTimestamp
	private Instant dhup;

	public DNIS(String dnis, String alias, String descricao) {
		this.dnis = dnis;
		this.alias = alias;
		this.descricao = descricao;
		this.status = StatusEn.APROVACAO;
	}

	public DNIS(Long id, String dnis, String alias, String descricao, StatusEn status) {
		this(dnis,alias,descricao);
		this.id = id;
		this.status = status;
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
		DNIS other = (DNIS) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "DNIS [id=" + id + ", dnis=" + dnis + ", alias=" + alias + ", status=" + status + ", descricao="
				+ descricao + ", dh=" + dh + ", dhup=" + dhup + "]";
	}

}
