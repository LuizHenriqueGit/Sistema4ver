package regras_negocio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import modelo.Convidado;
import modelo.Evento;
import modelo.Ingresso;
import modelo.Participante;
import repositorio.Repositorio;

public class Fachada {
	
	public static Repositorio repositorio = new Repositorio();
	private static int idint = 1;
	private static DateTimeFormatter formdata = DateTimeFormatter.ofPattern("dd/mm/yyyy");
	
	
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
		
		int id = getID();
		
		if (capacidade == 0 && preco == 0) {
			Evento evento = new Evento(id, data, descrição);
			repositorio.adicionar(evento);
		} else if (capacidade > 0 && preco == 0) {
			Evento evento = new Evento(id, data, descrição, capacidade);
			repositorio.adicionar(evento);
		} else if (capacidade == 0 && preco > 0) {
			Evento evento = new Evento(id, data, descrição, preco);
			repositorio.adicionar(evento);
		} else {
			Evento evento = new Evento(id, data, descrição, capacidade, preco);
			repositorio.adicionar(evento);
		}
		
		incrementoID();
		
	}
	
	// CRIAR PARTICIPANTE
	public static void criarParticipante(String cpf, String nascimento) throws Exception{
		
		if (cpf.length() == 0 || nascimento.length() == 0) {
			throw new Exception("Nenhum campo pode estar vazio.");
		}
		
		Participante participante = new Participante(cpf, nascimento);
		repositorio.adicionar(participante);
		
	}


	// CRIAR CONVIDADO
	public static void criarConvidado(String cpf, String nascimento, String empresa) throws Exception {
		
		if (cpf.length() == 0 || nascimento.length() == 0 || empresa.length() == 0) {
			throw new Exception("Nenhum campo pode estar vazio.");
		}
	
		if (empresa.length() <= 0) {
			throw new Exception("Nome da empresa e obrigatorio.");
		}
		
		Convidado convidado = new Convidado(cpf, nascimento, empresa);
		repositorio.adicionar(convidado);
	}
	
	//CRIAR INGRESSO
	public static void criarIngresso(int id, String cpf, String telefone) throws Exception{
		
		String codigo = id + "-" +  cpf;
		Evento evento = repositorio.localizarEvento(id);
		Participante participante = repositorio.localizarParticipante(cpf);
		
		Ingresso ingresso = new Ingresso(codigo, evento, participante, telefone);
		repositorio.adicionar(ingresso);
		evento.adicionarIngresso(ingresso);
		participante.adicionarIngresso(ingresso);
		
	}
	
	// APAGAR EVENTO
	public void apagarEvento(int id) throws Exception {
		
		ArrayList<Evento> eventos = repositorio.getEventos();
		Evento evento = null;
		
		for (Evento e : eventos) {
			if (e.getId() == id) {
				evento = e;
			}
		}
		
		if (evento == null) {
			throw new Exception("ID de evento invalido.");
		}
		
		if (evento.getId() == id && evento.getIngressos().size() == 0) {
			repositorio.remove(evento);
		} else {
			throw new Exception("O evento nao pode conter ingressos.");
		}
		
	}
	
	// APAGAR PARTICIPANTE
	
	public void apagarParticipante(String cpf) throws Exception {
		
		ArrayList<Participante> participantes = repositorio.getParticipantes();
		Participante participante = null;
		
		for (Participante p : participantes) {
			if (p.getCpf() == cpf) {
				participante = p;
			}
		}
		
		if (participante == null) {
			throw new Exception("CPF invalido.");
		}
		
		if (participante.getIngressos().size() > 0) {
			for (Ingresso i : participante.getIngressos()) {
				participante.removerIngresso(i);
			}	
		} 
		
		repositorio.remove(participante);
		
	}
	
	// APAGAR INGRESSO
	public static void apagarIngresso(String codigo) throws Exception {
		
		ArrayList<Participante> participantes = repositorio.getParticipantes();
		ArrayList<Evento> eventos = repositorio.getEventos();
		ArrayList<Ingresso> ingressos = repositorio.getIngressos();
		Ingresso ingresso = null;
		
		for (Ingresso i : ingressos) {
			if (i.getCodigo() == codigo) {
				ingresso = i;
			}
		}
		
		if (ingresso == null) {
			throw new Exception("Codigo invalido.");
		}
		
		for (Participante p : participantes) {
			if (p.getCpf().equals(codigo.split("-")[1])) {
				ingresso.setParticipante(null);;
			}
		}
		
		for (Evento e : eventos) {
			if (e.getId() == Integer.parseInt(codigo.split("-")[0])) {
				ingresso.setEvento(null);
			}
		}
		
		repositorio.remove(ingresso);
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
	
	// METODOS AUXILIARES
	
	public static void incrementoID() {
		idint++;
	}
	
	public static int getID() {
		return idint;
	}
	
}
	