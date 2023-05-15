package com.renato.listrest.models.entities;

import java.io.Serializable;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
public class ListaFree implements Serializable {
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

	@Column(nullable = false, updatable = true)
	@UpdateTimestamp
	private Instant dhup;

	public ListaFree(String fullfone) {
		this.fullfone = fullfone;
	}

	public ListaFree(String ddi, String ddd, String fone) {
		this.ddi = ddi;
		this.ddd = ddd;
		this.fone = fone;
		this.fullfone = ddi + ddd + fone;
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
		ListaFree other = (ListaFree) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "ListaFreeGeral [id=" + id + ", fullfone=" + fullfone + ", ddi=" + ddi + ", ddd=" + ddd + ", fone="
				+ fone + ", dh=" + this.getDh() + ", dhup=" + this.getDhup() + "]";
	}
	
	
}
