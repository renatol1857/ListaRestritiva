package com.renato.listrest.models.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbhistphone")
@NoArgsConstructor
@Getter
@Setter
public class HistFone implements Serializable{
	@Transient
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private Phone phone;
	@Column(nullable = false, updatable = false)
	@CreationTimestamp
	private Date dh;
	
	public HistFone(Phone phone) {
		super();
		this.phone = phone;
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
		HistFone other = (HistFone) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "HistFone [id=" + id + ", phone=" + phone + ", dh=" + dh + "]";
	}

	
	
	
	
}
