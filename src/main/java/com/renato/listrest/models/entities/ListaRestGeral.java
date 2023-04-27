package com.renato.listrest.models.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;

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
public class ListaRestGeral implements Serializable {
	@Transient
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 30)
	private String fullfone = "";
	@Column(length = 10)
	private String ddi = "";
	@Column(length = 10)
	private String ddd = "";
	@Column(length = 30)
	private String fone = "";

	@Column(updatable = false, nullable = false)
	@CreationTimestamp
	private Instant dh;

	public ListaRestGeral(String fullfone) {
		this.fullfone = fullfone;
	}
	
	public ListaRestGeral(String ddi, String ddd, String fone) {
		this.ddi = ddi;
		this.ddd = ddd;
		this.fone = fone;
		this.fullfone = ddi+ddd+fone;
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
		ListaRestGeral other = (ListaRestGeral) obj;
		return Objects.equals(id, other.id);
	}




}
