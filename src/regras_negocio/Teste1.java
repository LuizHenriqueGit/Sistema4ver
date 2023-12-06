package regras_negocio;

import modelo.Evento;

public class Teste1 {

	public static void main(String[] args) {
		
		try {
			System.out.println("\ncriando eventos");
			Fachada.criarEvento("24/11/2023","show da pisadinha",2,100.0);
			Fachada.criarEvento("24/06/2024","show de sao joao",2,200.0);
			Fachada.criarEvento("01/01/2024","fake",2,0.0);
			System.out.println("---------listagem de eventos");
			for(Evento e : Fachada.listarEventos()) 
				System.out.println(e);

		} catch (Exception e) {
			System.out.println("--->"+e.getMessage());
		}
}}

