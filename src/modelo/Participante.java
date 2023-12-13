/*
Alunos: Luiz Henrique e Lucas Pedro
*/

package modelo;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

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
	}
	
	public void removerIngresso(Ingresso ingresso) {
		ingressos.remove(ingresso);
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
		
		String resultado = "";
		
		for (Ingresso i : ingressos) {
			if (resultado.length() == 0) {
				resultado += "\nCodigo: " + i.getCodigo() + " - Telefone: " + i.getTelefone() + "\n";
			} else {
				resultado += "Codigo: " + i.getCodigo() + " - Telefone: " + i.getTelefone() + "\n";
			}
			
		}
		return "Participante [cpf=" + cpf + ", nascimento=" + nascimento + ", \ningressos:" + resultado + "]";
	}
	
	


	
	

}
