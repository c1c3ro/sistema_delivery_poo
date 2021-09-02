package negocios;

import java.util.Random;

public class Item {
	
	public String nome;
	public double valor;
	public String categoria;
	public String descricao;
	Random geradorDeID = new Random();
	public int ID;
	
	public Item(String nome, double valor, String categoria, String descricao) {
		
		this.nome = nome;
		this.valor = valor;
		this.categoria = categoria;
		this.descricao = descricao;
		ID = geradorDeID.nextInt(100000);
		
	}
	
	public int getID() {
		return this.ID;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public double getValor() {
		return this.valor;
	}
	
	public String getCategoria() {
		return this.categoria;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
	public void setValor(double novoValor) {
		this.valor = novoValor;
	}
	
	public void setNome(String novoNome) {
		this.nome = novoNome;
	}

}
