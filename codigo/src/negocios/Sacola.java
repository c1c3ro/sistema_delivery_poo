package negocios;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import Exceptions.UsuarioNaoEncontradoException;

public class Sacola {
	
	public Hashtable<Restaurante, ArrayList<Item>> itens;
	public Hashtable<Gerente, Integer> aprovacoes;
	public double total;
	public int status;
	/*
	 * status:
	 * 0 - esperando gerente(s) aprovar
	 * 2 - alguns gerentes ainda nao aprovaram
	 * 1 - aprovado pelo(s) gerente(s)
	 */
	
	public Sacola() {
		itens = new Hashtable<Restaurante, ArrayList<Item>>();
		aprovacoes = new Hashtable<Gerente, Integer>();
		total = 0.0;
		status = 0;
	}
	
	public double adicionarItem(Item item, Gerente gerente) {
		Restaurante restaurante = gerente.getRestaurante();
		ArrayList<Item> itensRestaurante;
		if (this.itens.containsKey(restaurante)) {
			itensRestaurante = this.itens.get(restaurante);
			itensRestaurante.add(item);
		} else {
			itensRestaurante = new ArrayList<Item>();
			itensRestaurante.add(item);
			this.itens.put(restaurante, itensRestaurante);
			this.aprovacoes.put(gerente, 0);
		}
		return this.setTotal(); // vai retornar total na sacola após a adição do item
	}
	
	public double removerItem(Item item, Gerente gerente) throws UsuarioNaoEncontradoException {
		Restaurante restaurante = gerente.getRestaurante();
		if (this.itens.containsKey(restaurante)) {
			ArrayList<Item> itensRestaurante = this.itens.get(restaurante);
			itensRestaurante.remove(item);
			if (itensRestaurante.size() == 0) {		
				// não sobrou nenhum item
				this.itens.remove(restaurante);
				this.aprovacoes.remove(gerente);
			}
			return this.setTotal(); // retorna o total após a remoção do item
		} else {
			throw new UsuarioNaoEncontradoException("Item não encontrado no cardápio");
		}
	}
	
	public double setTotal() {
		Enumeration<Restaurante> restaurantes = this.itens.keys();
		double total = 0.0;
		while (restaurantes.hasMoreElements()) {
			Restaurante key = restaurantes.nextElement();
			ArrayList<Item> restauranteItens = this.itens.get(key);
			
			for (int i = 0; i < restauranteItens.size(); i++) {
				total += restauranteItens.get(i).getValor();
			}
		}
		this.total = total;
		return this.total;
	}
	
	public int setStatusGerente(Gerente gerente, int status) {
		if (!this.aprovacoes.containsKey(gerente)) {
			return -1; // não encontrou o gerente
		} else {
			int aprGerente = this.aprovacoes.get(gerente);
			aprGerente = status;
			this.setStatus();
			double recebido = 0.0;
			Restaurante gerenteRestaurante = gerente.getRestaurante();
			ArrayList<Item> pedido = this.itens.get(gerenteRestaurante);
			for (int i = 0; i < pedido.size(); i++) {
				recebido += pedido.get(i).getValor();
			}
			gerente.getRestaurante().adicionarReceita(recebido);
			return aprGerente;
		}
	}
	
	public int setStatus() {
		Enumeration<Gerente> gerentes = this.aprovacoes.keys();
		int novoStatus = 0;
		while (gerentes.hasMoreElements()) {
			Gerente key = gerentes.nextElement();
			int apr = aprovacoes.get(key);
			
			if (apr == 1) {
				novoStatus++;
			}
		}
		
		if (novoStatus == this.aprovacoes.size()) {
			this.status = 1;
			return this.status;
		} else if (novoStatus > 0 && novoStatus < this.aprovacoes.size()) {
			this.status = 2;
			return this.status;
		} else {
			this.status = 0;
			return this.status;
		}
		
		//lembrar de verificar se o numero de restaurantes é maior que 0 nos negocios
	}
	
	public Hashtable<Restaurante, ArrayList<Item>> getItens() {
		return itens;
	}
	
	public double getTotal() {
		return total;
	}
	
	
	public int getStatus() {
		return this.status;
	}
	
	public Hashtable<Gerente, Integer> aprovacoesGerentes() {
		return this.aprovacoes; // para ver quais gerentes/restaurantes faltam aprovar pedidos
	}

}
