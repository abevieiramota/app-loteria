package br.com.abevieiramota.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "dezena")
public class Dezena {

	@Id
	@Column(name = "id_dezena")
	private Integer id;
	@Column(name = "inicio")
	private Integer inicio;
	@Column(name = "fim	")
	private Integer fim;
	@Column(name = "label")
	private String label;

	public String extract(String milhar) {
		return milhar.substring(this.inicio, this.fim);
	}

	@Override
	public String toString() {
		return this.label;
	}
}