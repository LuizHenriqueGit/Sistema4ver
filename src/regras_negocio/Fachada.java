package regras_negocio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import modelo.Convidado;
import modelo.Evento;
import modelo.Ingresso;
import modelo.Participante;
import repositorio.Repositorio;

public class Fachada {
	
	public static Repositorio repositorio = new Repositorio();
	
	private static DateTimeFormatter formdata = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	// CRIAR EVENTO
	public static void criarEvento (String data, String descrição, int capacidade, double preco) throws Exception{

		
		if (data.length() == 0 || capacidade == 0) {
			throw new Exception("Data e capacidade são obrigatorios.");
		}
		
		if (preco < 0) {
			throw new Exception("Preço não pode ser menor que 0.");
		}
		
		if (capacidade < 2) {
			throw new Exception("Capacidade nao pode ser menor que 2.");
		}
		
		int id = repositorio.gerarId();

		Evento evento = new Evento(id, data, descrição, capacidade, preco);
		repositorio.adicionar(evento);
	
		repositorio.salvarObjetos();	
	}
	
	// CRIAR PARTICIPANTE
	public static void criarParticipante(String cpf, String nascimento) throws Exception{
		
		if (cpf.length() == 0 || nascimento.length() == 0) {
			throw new Exception("Nenhum campo pode estar vazio.");
		}
		
		Participante participante = new Participante(cpf, nascimento);
		repositorio.adicionar(participante);
		repositorio.salvarObjetos();
		
	}


	// CRIAR CONVIDADO
	public static void criarConvidado(String cpf, String nascimento, String empresa) throws Exception {
		
		if (cpf.length() == 0 || nascimento.length() == 0 || empresa.length() == 0) {
			throw new Exception("Nenhum campo pode estar vazio.");
		}
	
		if (empresa.length() <= 0) {
			throw new Exception("Nome da empresa e obrigatorio.");
		}
		
		Participante convidado = new Convidado(cpf, nascimento, empresa);
		repositorio.adicionar(convidado);
		repositorio.salvarObjetos();
	}
	
	//CRIAR INGRESSO
	public static void criarIngresso(int id, String cpf, String telefone) throws Exception{
		
		String codigo = id + "-" +  cpf;
		Evento evento = repositorio.localizarEvento(id);
		Participante participante = repositorio.localizarParticipante(cpf);
		
		if (participante == null) {
			throw new Exception("CPF invalido.");
		}

		for (Ingresso i : repositorio.getIngressos()) {
			if (i.getCodigo().equals(codigo)) {
				throw new Exception("Ingresso já criado pra o CPF digitado.");
			}
		}
		
		if (evento.getIngressos().size() == evento.getCapacidade()) {
			throw new Exception("Evento cheio! Capacidade esgotada.");
		}
		
		Ingresso ingresso = new Ingresso(codigo, evento, participante, telefone);
		repositorio.adicionar(ingresso);
		evento.adicionarIngresso(ingresso);
		participante.adicionarIngresso(ingresso);
		repositorio.salvarObjetos();
	}
	
	// APAGAR EVENTO
	public static void apagarEvento(int id) throws Exception {
	
		Evento evento = repositorio.localizarEvento(id);
		
		if (evento == null) {
			throw new Exception("ID de evento invalido.");
		}
		
		if (evento.getIngressos().size() == 0) {
			repositorio.remover(evento);
		} else {
			throw new Exception("O evento nao pode conter ingressos.");
		}
		repositorio.salvarObjetos();
	}
	
	// APAGAR PARTICIPANTE
	public static void apagarParticipante(String cpf) throws Exception {
		
		Participante participante = repositorio.localizarParticipante(cpf);
		
		if (participante == null) {
			throw new Exception("CPF invalido.");
		}
		
		if (participante.getIngressos().size() > 0) {
			for (Ingresso i : participante.getIngressos()) {
				if (LocalDate.parse(i.getEvento().getData(), formdata).isBefore(LocalDate.now()))
				repositorio.remover(i);
			}	
		} 
		
		if (participante.getIngressos().size() == 0) {
			repositorio.remover(participante);
			repositorio.salvarObjetos();
		} else {
			throw new Exception("Participante ainda possui ingressos validos.");
		}
	}
	
	// APAGAR INGRESSO
	public static void apagarIngresso(String codigo) throws Exception {
		
		Ingresso ingresso = repositorio.localizarIngresso(codigo);
	
		
		repositorio.remover(ingresso);
		repositorio.salvarObjetos();
		
	}
	
	public static ArrayList<Evento> listarEventos() {
		return repositorio.getEventos();
	}
	
	public static ArrayList<Participante> listarParticipantes() {
		return repositorio.getParticipantes();
			
	}
	
	public static ArrayList<Ingresso> listarIngressos() {
		return repositorio.getIngressos();
	}
		
}
	