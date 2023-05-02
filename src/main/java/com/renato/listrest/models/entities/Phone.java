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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Phone implements Serializable{

	@Transient
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private DNIS dnis;
	@Column(length = 30)
	private String fullfone="";
	@Column(length = 10)
	private String ddi="";
	@Column(length = 10)
	private String ddd="";
	@Column(length = 30)
	private String fone="";
	@Column(nullable = false, updatable = false)
	@CreationTimestamp
	private Instant dh;
	@Column(nullable = false, updatable = false)
	@UpdateTimestamp
	private Instant dhup;
	
	public Phone(DNIS dnis, String fullfone) {
		this.dnis = dnis;
		this.fullfone = fullfone;
		this.ddi = "";
		this.ddd = "";
		this.fone = "";
	}
	
	public Phone(DNIS dnis, String ddi, String ddd, String fone) {
		this(dnis,ddi + ddd + fone);
		this.ddi = ddi;
		this.ddd = ddd;
		this.fone = fone;
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
		Phone other = (Phone) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Phone [id=" + id + ", dnis=" + dnis.getDnis() + ", fullfone=" + fullfone + ", ddi=" + ddi + ", ddd=" + ddd
				+ ", fone=" + fone + ", dh=" + dh + ", dhup=" + dhup + "]";
	}



	
	
}
