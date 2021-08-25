package negocios;

public class Restaurante {
	
	private String cnpj;
	private String nome;
	private String endereco;
	public Cardapio cardapio;
	public boolean aberto;
	
	public Restaurante(String cnpj, String nome, String endereco) {
		this.cnpj = cnpj;
		this.nome = nome;
		this.endereco = endereco;
		this.cardapio = new Cardapio();
		this.aberto = false;
	}
	
	public String getCnpj() {
		return this.cnpj;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public String getEndereco() {
		return this.endereco;
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
