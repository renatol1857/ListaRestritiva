package com.renato.listrest.models.entities;

import java.io.Serializable;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.renato.listrest.models.enums.TipoEndPointEn;

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
public class CodMsg implements Serializable {

	@Transient
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 10, nullable = false, updatable = false, unique = true)
	private String codMsg = "";

	@Column(length = 200)
	private String msg = "";

	@Column(updatable = false, nullable = false)
	@CreationTimestamp
	private Instant dh;

	@Column(updatable = true, nullable = false)
	@UpdateTimestamp
	private Instant dhUp;

	private TipoEndPointEn tipoEndPoint = TipoEndPointEn.CONSULTAR;

	@Column(length = 300)
	private String obs = "";

	public CodMsg(String codMsg) {
		this.codMsg = codMsg;
	}

	public CodMsg(String codMsg, String msg) {
		this(codMsg);
		this.msg = msg;
	}

	public CodMsg(String codMsg, String msg, TipoEndPointEn tipoEndPoint) {
		this(codMsg, msg);
		this.tipoEndPoint = tipoEndPoint;
	}

	public CodMsg(String codMsg, String msg, TipoEndPointEn tipoEndPoint, String obs) {
		this(codMsg, msg, tipoEndPoint);
		this.obs = obs;
	}

	public String getDh() {
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").withZone(ZoneId.systemDefault());
		return fmt.format(this.dh);
	}

	public String getDhup() {
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").withZone(ZoneId.systemDefault());
		return fmt.format(this.dhUp);
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
		CodMsg other = (CodMsg) obj;
		return Objects.equals(id, other.id);
	}

}
