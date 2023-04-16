package com.renato.listrest.models.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;

import com.renato.listrest.models.enums.StatusEn;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name = "tbDNIS")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class DNIS implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 30,nullable = false,unique = true, updatable = false)
	private String dnis;

	@Column(length = 50)
	private String alias;
	
	private StatusEn status =StatusEn.INATIVO;

	@Column(length = 200)
	private String descricao;
	
	@Column(nullable = false, updatable = false)
	@CreationTimestamp
	private Date dh;
	
	public DNIS( String dnis, String alias, String descricao) {
		this.dnis = dnis;
		this.alias = alias;
		this.descricao = descricao;
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
	
}
