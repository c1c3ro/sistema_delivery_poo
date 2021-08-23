package negocios;

public class Cliente extends UsuarioAbstrato {
	
	// Dados do cliente
	private String cpf;
	private String endereco;
	private double carteira;
	private Sacola sacola;
	
	public Cliente(String nome, String cpf, String senha, String endereco) {
		super(nome, senha);
		this.cpf = cpf;
		this.endereco = endereco;
		this.carteira = 0;
		this.sacola = new Sacola();
	}
	
	public double adicionarDinheiro(double valor) {
		this.carteira += valor;
		
		return this.carteira; // retorna o valor atual da carteira após a adição
	}
	
	public void fazerPedido(double valor) {
		
		return;
	}
	
	public String atualizarEndereco(String enderecoNovo) {
		this.endereco = enderecoNovo;
		
		return this.endereco; // retorna o endereco após a atualização
		
	}
	
	// Getters and Setters
	public String getEndereco() {
		return this.endereco;
	}
	
	public double getCarteira() {
		return this.carteira;
	}
	
	public Sacola getSacola() {
		return this.sacola;
	}

}
