package modelo;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

public class Participante {

	private String cpf;
	private String nascimento;
	private ArrayList<Ingresso> ingressos = new ArrayList<>();
	private static DateTimeFormatter formdata = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public Participante(String cpf, String nascimento) {
		this.cpf = cpf;
		this.nascimento = nascimento;
	}
	
	public int calcularIdade() {
		LocalDate date = LocalDate.parse(nascimento, formdata);
		Period idade = Period.between(date, LocalDate.now());
		
		return idade.getYears();
	}
	
	
	public void adicionarIngresso(Ingresso ingresso) {
		ingressos.add(ingresso);
		ingresso.setParticipante(this);
	}
	
	public void removerIngresso(Ingresso ingresso) {
		ingressos.remove(ingresso);
		ingresso.setParticipante(null);
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

	@Override
	public String toString() {
		return "Participante [cpf=" + cpf + ", nascimento=" + nascimento + ", ingressos=" + ingressos.toString() + "]";
	}
	
	


	
	

}
