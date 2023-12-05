package modelo;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Participante {

	private String cpf;
	private String nascimento;
	private ArrayList<Ingresso> ingressos;
	private static DateTimeFormatter formdata = DateTimeFormatter.ofPattern("dd/mm/yyyy");

	public Participante(String cpf, String nascimento) {
		this.cpf = cpf;
		this.nascimento = nascimento;
	}
	
	public int calcularIdade() {
		LocalDate date = LocalDate.parse(nascimento, formdata);
		Period idade = Period.between(date, LocalDate.now());
		
		return idade.getYears();
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
