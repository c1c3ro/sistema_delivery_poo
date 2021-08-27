package negocios;

public class Gerente extends UsuarioAbstrato {
	
	private String cpf;
	private Restaurante restaurante;
	
	public Gerente(String nome, String cpf, String senha, Restaurante restaurante) {
		super(nome, senha);
		this.cpf = cpf;
		this.restaurante = restaurante;
	}
	
	public boolean aprovarPedido(Sacola sacola) {
		
		return true;
	}

}
