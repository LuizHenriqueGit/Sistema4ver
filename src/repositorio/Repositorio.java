package repositorio;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import modelo.Convidado;
import modelo.Evento;
import modelo.Ingresso;
import modelo.Participante;

public class Repositorio {
	
	private ArrayList<Participante> participantes = new ArrayList<>();
	private ArrayList<Evento> eventos = new ArrayList<>();
	private ArrayList<Ingresso> ingressos = new ArrayList<>();
	
	public Repositorio() {
		carregarObjetos();
	}
	
	public void adicionar(Participante participante) {
		participantes.add(participante);
	}
	
	public void adicionar(Evento evento) {
		eventos.add(evento);
	}

	public void adicionar(Ingresso ingresso) {
		ingressos.add(ingresso);
	}
	
	public void carregarObjetos()  	{
		// carregar para o repositorio os objetos salvos nos arquivos csv
		try {
			//caso os arquivos nao existam, serao criados vazios
			File f1 = new File( new File(".\\eventos.csv").getCanonicalPath() ) ; 
			File f2 = new File( new File(".\\participantes.csv").getCanonicalPath() ) ; 
			File f3 = new File( new File(".\\ingressos.csv").getCanonicalPath() ) ; 
			if (!f1.exists() || !f2.exists() || !f3.exists()) {
				//System.out.println("criando arquivo .csv vazio");
				FileWriter arquivo1 = new FileWriter(f1); arquivo1.close();
				FileWriter arquivo2 = new FileWriter(f2); arquivo2.close();
				FileWriter arquivo3 = new FileWriter(f3); arquivo3.close();
				return;
			}
		}
		catch(Exception ex)		{
			throw new RuntimeException("criacao dos arquivos vazios:"+ex.getMessage());
		}

	}
	
	
	public void	salvarObjetos()  {
		//gravar nos arquivos csv os objetos que estão no repositório
		try	{
			File f = new File( new File(".\\eventos.csv").getCanonicalPath())  ;
			FileWriter arquivo1 = new FileWriter(f); 
			for(Evento e : eventos) 	{
				arquivo1.write(e.getId()+";"+e.getData()+";"+e.getDescricao()+";"+e.getCapacidade()+";"+e.getPreco()+"\n");	
			} 
			arquivo1.close();
		}
		catch(Exception e){
			throw new RuntimeException("problema na criação do arquivo  eventos "+e.getMessage());
		}

		try	{
			File f = new File( new File(".\\participantes.csv").getCanonicalPath())  ;
			FileWriter arquivo2 = new FileWriter(f) ; 
			for(Participante p : participantes) {
				if(p instanceof Convidado c )
					arquivo2.write(p.getCpf() +";" + p.getNascimento() +";" + c.getEmpresa()+"\n");	
				else
					arquivo2.write(p.getCpf() +";" + p.getNascimento() +"\n");	

			} 
			arquivo2.close();
		}
		catch (Exception e) {
			throw new RuntimeException("problema na criação do arquivo  participantes "+e.getMessage());
		}
		
		try	{
			File f = new File( new File(".\\ingressos.csv").getCanonicalPath())  ;
			FileWriter arquivo3 = new FileWriter(f) ; 
			for(Ingresso i : this.getIngressos()) {
					arquivo3.write(i.getCodigo() +";" + i.getTelefone()+"\n");	

			} 
			arquivo3.close();
		}
		catch (Exception e) {
			throw new RuntimeException("problema na criação do arquivo  participantes "+e.getMessage());
		}
		
	}

	public ArrayList<Ingresso> getIngressos() {
		return ingressos;
	}
}
