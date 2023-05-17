package com.renato.listrest.models.entities;

import java.io.Serializable;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
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
@Getter
@Setter
@NoArgsConstructor
public class HistFree implements Serializable {
	@Transient
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(cascade = CascadeType.ALL)
	private ListaFree free;

	@Column(nullable = false, updatable = false)
	@CreationTimestamp
	private Instant dh;

	@Column(length = 50)
	private String ip = "127.0.0.1";

	@Column(length = 100)
	private String obs = "";
	
	public HistFree(ListaFree free) {
		this.free = free;
	}

	public HistFree(ListaFree lstFree, String ip) {
		this(lstFree);
		if (ip.isEmpty())
			ip = "127.0.0.1";
		if (ip.equals("0:0:0:0:0:0:0:1"))
			ip = "127.0.0.1";
		this.ip = ip;
	}

	public HistFree(ListaFree lstFree, String ip, String obs) {
		this(lstFree, ip);
		this.obs = obs;
	}

	public String getDh() {
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").withZone(ZoneId.systemDefault());
		return fmt.format(this.dh);
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
		HistFree other = (HistFree) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "HistFreeGeral [id=" + id + ", dh=" + dh + ", ip=" + ip + ", obs=" + obs + "]";
	}

}
