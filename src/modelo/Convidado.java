/*
Alunos: Luiz Henrique e Lucas Pedro
*/

package modelo;

public class Convidado extends Participante {

	private String empresa;

	public Convidado(String cpf, String nascimento, String empresa) {
		super(cpf, nascimento);
		this.empresa = empresa;
	}

	// Getters e Setters
	
	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
}
