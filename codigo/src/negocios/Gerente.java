package negocios;

import java.util.ArrayList;
import java.util.Hashtable;

public class Gerente extends UsuarioAbstrato {
	
	private Restaurante restaurante;
	private Hashtable<Sacola, ArrayList<Item>> pedidosParaAprovacao;
	
	public Gerente(String nome, String cpf, String senha, Restaurante restaurante) {
		super(nome, senha, cpf);
		this.restaurante = restaurante;
	}
	
	public int aprovarPedido(Sacola sacola) {
		
		return sacola.setStatusGerente(this, 1);
		
	}
	
	public void adicionarPedidoParaAprovacao(Sacola sacola, ArrayList<Item> pedido) {
		this.pedidosParaAprovacao.put(sacola, pedido);
	}
	
	public void removerPedidoParaAprovacao(Sacola sacola) {
		this.pedidosParaAprovacao.remove(sacola);
	}
	
	public Hashtable<Sacola, ArrayList<Item>> getPedidosParaAprovacao() {
		return this.pedidosParaAprovacao;
	}

	
	public Restaurante getRestaurante() {
		return restaurante;
	}

}
