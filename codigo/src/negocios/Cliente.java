package negocios;

import java.io.Serializable;
import java.util.ArrayList;

public class Cliente extends UsuarioAbstrato implements Serializable {
	
	// Dados do cliente
	private String endereco;
	private double carteira;
	private Sacola sacola;
	private ArrayList<Sacola> pedidosAntigos;
	
	public Cliente(String nome, String cpf, String senha, String endereco) {
		super(nome, senha, cpf);
		this.endereco = endereco;
		this.carteira = 0;
		this.sacola = new Sacola();
		pedidosAntigos = new ArrayList<Sacola>();
	}
	
	public double adicionarDinheiro(double valor) {
		this.carteira += valor;
		
		return this.carteira; // retorna o valor atual da carteira após a adição
	}
	
	public double fazerPedido(double valor) {
		this.carteira -= valor;
		
		pedidosAntigos.add(this.sacola);
		this.sacola = new Sacola();
		
		return this.carteira;
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
	
	public ArrayList<Sacola> getPedidosAntigos() {
		return this.pedidosAntigos;
	}
	
	public Sacola pedidoAtivoMaisRecente() {
		Sacola pedido = null;
		for (int i = this.pedidosAntigos.size(); i > 0; i--) {
			pedido = this.pedidosAntigos.get(i);
			int pedidoStatus = pedido.getStatus();
			if (pedidoStatus == 0 || pedidoStatus == 2) {
				return pedido;
			}
		}
		return null;
	}

}
