package modelo;

public class Ingresso {

	private String codigo;
	private String telefone;
	private Evento evento;
	private Participante participante;

	public Ingresso(Evento evento, Participante participante, String telefone) {
		this.telefone = telefone;
		this.evento = evento;
		this.participante = participante;
	}


	public double calcularPreco() {
		return 1;
	}
}
