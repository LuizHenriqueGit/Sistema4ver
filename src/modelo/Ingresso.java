/*
Alunos: Luiz Henrique e Lucas Pedro
*/

package modelo;


public class Ingresso {

	private String codigo;
	private String telefone;
	private Evento evento;
	private Participante participante;

	public Ingresso(String codigo, Evento evento, Participante participante, String telefone) {
		this.codigo = codigo;
		this.telefone = telefone;
		this.evento = evento;
		this.participante = participante;
	}

	public double calcularPreco() {
	
		if (participante.calcularIdade() < 18) {
			if (participante instanceof Convidado) {
				return evento.getPreco() * 0.4;
			}
			return evento.getPreco() * 0.9;
		} else if (participante.calcularIdade() >= 60) {
			if (participante instanceof Convidado) {
				return evento.getPreco() * 0.3;
			}
			return evento.getPreco() * 0.8;
		} else {
			if (participante instanceof Convidado) {
				return evento.getPreco() * 0.5;
			}
			return evento.getPreco();
		}
	}
	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Evento getEvento() {
		return evento;
	}
	
	public Participante getParticipante() {
		return participante;
	}

	
	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public void setParticipante(Participante participante) {
		this.participante = participante;
	}
	
	@Override
	public String toString() {
		return "Ingresso [codigo=" + codigo + ", telefone=" + telefone + "]";
	}
	
	
	
}
