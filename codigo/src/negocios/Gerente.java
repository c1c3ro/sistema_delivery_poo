package negocios;

public class Gerente extends UsuarioAbstrato {
	
	private Restaurante restaurante;
	
	public Gerente(String nome, String cpf, String senha, Restaurante restaurante) {
		super(nome, senha);
		this.restaurante = restaurante;
	}
	
	public int aprovarPedido(Sacola sacola) {
		
		return sacola.setStatusGerente(this, 1);
		
	}

	
	public Restaurante getRestaurante() {
		return restaurante;
	}

}
