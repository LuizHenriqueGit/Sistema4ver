package regras_negocio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
	public static void criarEvento (String data, String descrição, String capacidade, String preco) throws Exception{

		
		if (data.length() == 0 || capacidade.length() == 0) {
			throw new Exception("Data e capacidade são obrigatorios.");
		}
		
		if (Integer.parseInt(preco) < 0) {
			throw new Exception("Preço não pode ser menor que 0.");
		}
		
		if (Integer.parseInt(capacidade) < 2) {
			throw new Exception("Capacidade nao pode ser menor que 2.");
		}
		
		int id = getID();
		
		if (capacidade.length() == 0 && preco.length() == 0) {
			Evento evento = new Evento(id, data, descrição);
			repositorio.adicionar(evento);
		} else if (capacidade.length() > 0 && preco.length() == 0) {
			Evento evento = new Evento(id, data, descrição, Integer.parseInt(capacidade));
			repositorio.adicionar(evento);
		} else if (capacidade.length() == 0 && preco.length() > 0) {
			Evento evento = new Evento(id, data, descrição, Double.parseDouble(preco));
			repositorio.adicionar(evento);
		} else {
			Evento evento = new Evento(id, data, descrição, Integer.parseInt(capacidade), Double.parseDouble(preco));
			repositorio.adicionar(evento);
		}
		
		incrementoID();
		
	}
	
	// CRIAR PARTICIPANTE
	public static void criarParticipante(String cpf, String nascimento) throws Exception{
		
		if (cpf.length() == 0 || nascimento.length() == 0) {
			throw new Exception("Nenhum campo pode estar vazio.");
		}
		
		if (cpf.length() != 11) {
			throw new Exception("CPF invalido.");
		}
		
		try {
			LocalDate.parse(nascimento, formdata);
		} catch (DateTimeParseException e) {
			throw new Exception("Formato de data invalido.");
		}
		
		Participante participante = new Participante(cpf, nascimento);
		repositorio.adicionar(participante);
		
	}


	// CRIAR CONVIDADO
	public static void criarConvidado(String cpf, String nascimento, String empresa) throws Exception {
		
		if (cpf.length() == 0 || nascimento.length() == 0 || empresa.length() == 0) {
			throw new Exception("Nenhum campo pode estar vazio.");
		}
		
		if (cpf.length() != 11) {
			throw new Exception("CPF invalido.");
		}
		
		try {
			LocalDate.parse(nascimento, formdata);
		} catch (DateTimeParseException e) {
			throw new Exception("Formato de data invalido.");
		}
		
		if (empresa.length() <= 0) {
			throw new Exception("Nome da empresa e obrigatorio.");
		}
		
		Convidado convidado = new Convidado(cpf, nascimento, empresa);
		repositorio.adicionar(convidado);
	}
	
	//CRIAR INGRESSO
	public static void criarIngresso(int id, String cpf, String telefone) {
		
		String codigo = id + "-" +  cpf;
		
		Ingresso ingresso = new Ingresso(codigo, evento, participante, telefone);
	}
	
	// METODOS AUXILIARES
	
	public static void incrementoID() {
		idint++;
	}
	
	public static int getID() {
		return idint;
	}
}
	