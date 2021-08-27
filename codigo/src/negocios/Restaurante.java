package negocios;

public class Restaurante {
	
	private String cnpj;
	private String nome;
	public Cardapio cardapio;
	public boolean aberto;
	
	public Restaurante(String cnpj, String nome) {
		this.cnpj = cnpj;
		this.nome = nome;
		this.cardapio = new Cardapio();
		this.aberto = false;
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
	

}
