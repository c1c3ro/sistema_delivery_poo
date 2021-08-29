package negocios;

public class Restaurante {
	
	private String cnpj;
	private String nome;
	public Cardapio cardapio;
	public boolean aberto;
	private double receita;
	public Gerente gerente;
	
	public Restaurante(String cnpj, String nome) {
		this.cnpj = cnpj;
		this.nome = nome;
		this.cardapio = new Cardapio();
		this.aberto = false;
		receita = 0.0;
	}
	
	public double getReceita() {
		return this.receita;
	}
	
	public String getCnpj() {
		return this.cnpj;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public Cardapio getCardapio() {
		return this.cardapio;
	}
	
	public boolean estaAberto() {
		return this.aberto;
	}
	
	public void abrir() {
		this.aberto = true;
	}
	
	public void fechar() {
		this.aberto = false;
	}	
	
	@Override
	public String toString() {
		return this.getNome();
	}
	
	public double adicionarReceita(double recebido) {
		this.receita += recebido;
		return this.receita;
	}
	

}
