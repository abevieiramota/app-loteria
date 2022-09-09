package br.com.abevieiramota.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "configuracao")
public class Configuracao {

	@Id
	@Column(name = "id_configuracao")
	private Integer id;
	
	@Column(name = "data_ultima_atualizacao")
	private String dataUltimaAtualizacao;
	
	@ManyToOne
	@JoinColumn(name = "id_turno_ultima_atualizacao")
	private Turno turnoUltimaAtualizacao;
	
	@OneToOne
	@JoinColumn(name = "id_loteria")
	private Loteria loteria;

	@Override
	public String toString() {
		return this.id.toString();
	}
}
