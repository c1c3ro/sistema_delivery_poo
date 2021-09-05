package negocios;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;

public class Gerente extends UsuarioAbstrato implements Serializable {
	
	private Restaurante restaurante;
	private Hashtable<Sacola, ArrayList<Item>> pedidosParaAprovacao;
	
	public Gerente(String nome, String cpf, String senha, Restaurante restaurante) {
		super(nome, senha, cpf);
		this.restaurante = restaurante;
		this.pedidosParaAprovacao = new Hashtable<Sacola, ArrayList<Item>>();
	}
	
	public int aprovarPedido(Sacola sacola) {
		
		return sacola.setStatusGerente(this, 1);
		
	}
	
	public void adicionarPedidoParaAprovacao(Sacola sacola, ArrayList<Item> pedido) {
		if (pedido.size() == 0) {
			System.out.println("Pedido para "+this.getNome()+" vazio!");
		} else {
			System.out.println("Pedido sendo adicionado para "+this.getNome()+" aprovar no seu restaurante "+this.getRestaurante().getNome()+":");
			for (int i = 0; i < pedido.size(); i++) {
				System.out.println(pedido.get(i).toString());
			}
		}
		this.pedidosParaAprovacao.put(sacola, pedido);
	}
	
	public void removerPedidoParaAprovacao(Sacola sacola) {
		this.pedidosParaAprovacao.remove(sacola);
	}
	
	public Hashtable<Sacola, ArrayList<Item>> getPedidosParaAprovacao() {
		System.out.println("Quantidade de pedidos para aprovação: "+this.pedidosParaAprovacao.size());
		return this.pedidosParaAprovacao;
	}

	
	public Restaurante getRestaurante() {
		return restaurante;
	}

}
