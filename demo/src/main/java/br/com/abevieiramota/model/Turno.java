package br.com.abevieiramota.model;

import java.util.Collection;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "turno")
public class Turno {

	@Id
	@Column(name = "id_turno")
	private Integer id;
	@Column(name = "label")
	private String label;
	@Column(name = "hora_min")
	private Integer horaMinima;
	@Column(name = "hora_max")
	private Integer horaMaxima;
	@ManyToOne
	@JoinColumn(name = "id_loteria")
	private Loteria loteria;

	public static Turno getTurnoByHoraPart(String time, Collection<Turno> turnos) {
		Integer horaPartAsInt = Integer.parseInt(time.split(":")[0]);

		Collection<Turno> turnosFitted = turnos
			.stream()
			.filter(t -> horaPartAsInt <= t.horaMaxima && horaPartAsInt >= t.horaMinima)
			.toList();

		if (turnosFitted.isEmpty()) {
			throw new IllegalStateException(
					String.format("Não existe turno registrado no sistema para o horário [%s]", time));
		}

		if (turnosFitted.size() > 1) {
			throw new IllegalStateException(
				String.format(
					"Mais de um turno inclui o horário [%s]. Turnos [%s]", 
					time,
					turnosFitted.stream().map(Turno::getLabel).collect(Collectors.joining(", "))
				)
			);
		}
		
		return turnosFitted.iterator().next();
	}

	@Override
	public String toString() {
		return this.label;
	}

	@Override
	public boolean equals(Object other) {

		if (!(other instanceof Turno)) {
			return false;
		}

		Turno otherTurno = (Turno) other;

		return this.id == otherTurno.id;
	}

	@Override
	public int hashCode() {

		return this.id.hashCode();
	}
}