package fachada;
import java.util.ArrayList;
import java.util.Hashtable;

import Exceptions.ClienteJaExisteException;
import Exceptions.SemDinheiroException;
import Exceptions.UsuarioNaoEncontradoException;

import negocios.*;

public class Delivery {
	
	private NegociosCliente clientes;
	private NegociosGerente gerentes;
	private NegociosRestaurante restaurantes;
	
	public Delivery() {
		this.clientes = new NegociosCliente();
		this.gerentes = new NegociosGerente();
		this.restaurantes = new NegociosRestaurante();
	}
	
	// Client Methods
	
	public void cadastrarCliente(String nome, String cpf, String senha, String endereco) throws ClienteJaExisteException {
		clientes.cadastrarCliente(nome, cpf, senha, endereco);
	}
	
	public Cliente matchLoginSenhaCliente(String cpf, String senha) throws UsuarioNaoEncontradoException {
		return clientes.matchLoginSenha(cpf, senha);
	}
	
	public void adicionarItemNaSacola(String cpf, Item item, Gerente gerente) throws UsuarioNaoEncontradoException {
		clientes.adicionarItemNaSacola(cpf, item, gerente);
	}
	
	public void removerItemDaSacola(String cpf, Item item, Gerente gerente) throws UsuarioNaoEncontradoException {
		clientes.removerItemDaSacola(cpf, item, gerente);
	}
	
	public void esvaziarSacola(String cpf) throws UsuarioNaoEncontradoException {
		clientes.esvaziarSacola(cpf);
	}
	
	public ArrayList<Sacola> pedidosAntigos(String cpf) throws UsuarioNaoEncontradoException {
		return clientes.pedidosAntigos(cpf);
	}
	
	public Sacola pedidoAtivoMaisRecente(String cpf) throws UsuarioNaoEncontradoException {
		return clientes.pedidoAtivoMaisRecente(cpf);
	}
	
	public int getStatusPedidos(String cpf) throws UsuarioNaoEncontradoException {
		return clientes.getStatusPedidos(cpf);
	}
	
	public Hashtable<Gerente, Integer> gerentesQueAutorizaram(String cpf) throws UsuarioNaoEncontradoException {
		return clientes.gerentesQueAutorizaram(cpf);
	}
	
	public double adicionarDinheiro(String cpf, double valor) throws UsuarioNaoEncontradoException {
		return clientes.adicionarDinheiro(cpf, valor);
	}
	
	public double realizarCompra(String cpf) throws UsuarioNaoEncontradoException, SemDinheiroException {
		return clientes.realizarCompra(cpf);
	}
	
	// Manager Methods
	
	public void cadastrarGerente(String nome, String cpf, String senha, String restauranteCnpj, String restauranteNome, NegociosRestaurante negocioRestaurante) throws ClienteJaExisteException {
		gerentes.cadastrarGerente(nome, cpf, senha, restauranteCnpj, restauranteNome, negocioRestaurante);
	}
	
	public Gerente matchLoginSenhaGerente(String cpf, String senha) throws UsuarioNaoEncontradoException {
		return gerentes.matchLoginSenha(cpf, senha);
	}
	
	public Restaurante getRestaurante(String cpfDoGerente) throws UsuarioNaoEncontradoException {
		return gerentes.getRestaurante(cpfDoGerente);
	}
	
	public void abrirRestaurante(Gerente gerente) {
		gerentes.abrirRestaurante(gerente);
	}

	public void fecharRestaurante(Gerente gerente) {
		gerentes.fecharRestaurante(gerente);
	}
	
	public void inserirProdutoNoCardapio(Restaurante restaurante, String nome, double valor, String categoria, String descricao) throws ClienteJaExisteException, UsuarioNaoEncontradoException {
		restaurantes.inserirProdutoNoCardapio(restaurante, nome, valor, categoria, descricao);
	}
	
	public void removerProdutoDoCardapio(Restaurante restaurante, int ID) throws UsuarioNaoEncontradoException {
		restaurantes.removerProdutoDoCardapio(restaurante, ID);
	}
	
	public ArrayList<Restaurante> restaurantesAbertos() {
		return restaurantes.restaurantesAbertos();
	}
	
	public Hashtable<Integer, Item> getItensDoCardapio(Restaurante restaurante) throws UsuarioNaoEncontradoException {
		return restaurantes.getItensDoCardapio(restaurante);
	}
	
	public ArrayList<String> getCategorias(String cnpj) throws UsuarioNaoEncontradoException {
		return restaurantes.getCategorias(cnpj);
	}
	
	public double ganhos(String cnpj) throws UsuarioNaoEncontradoException {
		return restaurantes.ganhos(cnpj);
	}
	
	public Gerente pesquisarGerentePorRestaurante(Restaurante restaurante) {
		return gerentes.pesquisarGerentePorRestaurante(restaurante);
	}

	public NegociosRestaurante getRestaurantes() {
		return restaurantes;
	}

	public void setRestaurantes(NegociosRestaurante restaurantes) {
		this.restaurantes = restaurantes;
	}
	
}
