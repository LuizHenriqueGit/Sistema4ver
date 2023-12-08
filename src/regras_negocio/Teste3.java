package regras_negocio;

import modelo.Convidado;
import modelo.Participante;

public class Teste3 {

	public static void main(String[] args) {
		try {
			System.out.println("\ncriando participantes e convidados");
			Fachada.criarParticipante("1111", "01/01/1960");
			Fachada.criarParticipante("2222", "01/01/2000");
			Fachada.criarParticipante("3333", "01/01/2010");
			Fachada.criarConvidado("4444", "01/01/1960", "empresa1");
			Fachada.criarConvidado("5555", "01/01/2000", "empresa2");
			Fachada.criarConvidado("6666", "01/01/2010", "empresa3");
			Fachada.criarConvidado("7777", "01/01/2010", "empresa3");
			System.out.println("---------listagem de participantes e convidados -----");
			for(Participante p : Fachada.listarParticipantes()) 
				if (p instanceof Convidado) {
					System.err.println("Convidado");
				} else {
					System.out.println("Participante");
				};

		} catch (Exception e) {
			System.out.println("--->"+e.getMessage());
		}		

	}

}
