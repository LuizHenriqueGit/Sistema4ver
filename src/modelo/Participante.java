package modelo;

import java.util.ArrayList;

public class Participante {

	private String cpf;
	private String nascimento;
	private ArrayList<Ingresso> ingressos;

	public Participante(String cpf, String nascimento) {
		this.cpf = cpf;
		this.nascimento = nascimento;
	}
	
	// Getters e Setters

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNascimento() {
		return nascimento;
	}

	public void setNascimento(String nascimento) {
		this.nascimento = nascimento;
	}

	public ArrayList<Ingresso> getIngressos() {
		return ingressos;
	}

	public void setIngressos(ArrayList<Ingresso> ingressos) {
		this.ingressos = ingressos;
	}

}
