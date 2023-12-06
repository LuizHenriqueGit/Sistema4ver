package modelo;

import java.util.ArrayList;

public class Evento {

	private int id;
	private String data;
	private String descricao;
	private int capacidade;
	private double preco;
	private ArrayList<Ingresso> ingressos = new ArrayList<>();
	
	public Evento() {};
	
	public Evento(int id, String data, String descricao) {
		this.id = id;
		this.data = data;
		this.descricao = descricao;	
	}
	
	public Evento(int id, String data, String descricao, int capacidade) {
		this.id = id;
		this.data = data;
		this.descricao = descricao;	
		this.capacidade = capacidade;
	}
	
	public Evento(int id, String data, String descricao, double preco) {
		this.id = id;
		this.data = data;
		this.descricao = descricao;	
		this.preco = preco;
	}

	public Evento(int id, String data, String descricao, int capacidade, double preco) {
		this.id = id;
		this.data = data;
		this.descricao = descricao;
		this.capacidade = capacidade;
		this.preco = preco;
		
	}

	public boolean lotado() {

		if (ingressos.size() >= capacidade) {
			return true;
		}
		return false;
	}


	public int quantidadeIngressos() {

		return ingressos.size();
	}

	public double totalArrecadado() {

		return ingressos.size() * preco;
	}

	public void adicionarIngresso(Ingresso ingresso) {
		ingressos.add(ingresso);
		ingresso.setEvento(this);
	}
	
	public void removerIngresso(Ingresso ingresso) {
		ingressos.remove(ingresso);
		ingresso.setEvento(null);
	}
	
	// Getters e Setters
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public ArrayList<Ingresso> getIngressos() {
		return ingressos;
	}

	public void setIngressos(ArrayList<Ingresso> ingressos) {
		this.ingressos = ingressos;
	}

}
