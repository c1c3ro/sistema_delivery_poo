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
	
	public void adicionarDinheiro(double valor) {
		
		return;
	}
	
	public void fazerPedido(double valor) {
		
		return;
	}
	
	public void atualizarEndereco() {
		
	}

}
