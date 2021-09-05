package fachada;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;

import Exceptions.ClienteJaExisteException;
import Exceptions.OpcaoInvalidaException;
import Exceptions.SacolaVaziaException;
import Exceptions.SemDinheiroException;
import Exceptions.UsuarioNaoEncontradoException;

import negocios.*;

public class Delivery implements Serializable {
	
	private NegociosCliente clientes;
	private NegociosGerente gerentes;
	private NegociosRestaurante restaurantes;
	private String filename;
	
	public Delivery() {
		this.clientes = new NegociosCliente();
		this.gerentes = new NegociosGerente();
		this.restaurantes = new NegociosRestaurante();
		this.filename = "delivery.ser";
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
	
	public String atualizarCliente(String cpf, int campo, String novoValor) throws UsuarioNaoEncontradoException, OpcaoInvalidaException {
		return clientes.atualizarCliente(cpf, campo, novoValor);
	}
	
	public Sacola getSacolaAtual(String cpf) throws UsuarioNaoEncontradoException {
		return clientes.getSacolaAtual(cpf);
	}
	
	public Hashtable<Restaurante, ArrayList<Item>> getItensNaSacolaPorRestaurante(Sacola sacola) throws SacolaVaziaException {
		return clientes.getItensNaSacolaPorRestaurante(sacola);
	}
	
	public ArrayList<Item> getItensNaSacola(Sacola sacola) throws SacolaVaziaException {
		return clientes.getItensNaSacola(sacola);
	}
	
	public double getTotalSacola(Sacola sacola) throws SacolaVaziaException {
		return clientes.getTotalSacola(sacola);
	}
	
	// Manager Methods
	
	public void cadastrarGerente(String nome, String cpf, String senha, String restauranteCnpj, String restauranteNome, NegociosRestaurante negocioRestaurante) throws ClienteJaExisteException {
		gerentes.cadastrarGerente(nome, cpf, senha, restauranteCnpj, restauranteNome, negocioRestaurante);
	}
	
	public Gerente matchLoginSenhaGerente(String cpf, String senha) throws UsuarioNaoEncontradoException {
		return gerentes.matchLoginSenha(cpf, senha);
	}
	
	public String atualizarGerente(String cpf, int campo, String novoValor) throws UsuarioNaoEncontradoException, OpcaoInvalidaException {
		return gerentes.atualizarGerente(cpf, campo, novoValor);
	}
	
	public boolean aprovarPedido(Gerente gerente, Sacola sacola) throws UsuarioNaoEncontradoException {
		return gerentes.aprovarPedido(gerente, sacola);
	}
	
	public Hashtable<Sacola, ArrayList<Item>> getPedidosParaAprovacao(Gerente gerente) throws UsuarioNaoEncontradoException {
		return gerentes.getPedidosParaAprovacao(gerente);
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
	
	public Item getItemPorID(Restaurante restaurante, int ID) throws UsuarioNaoEncontradoException {
		return restaurantes.getItemPorID(restaurante, ID);
	}
	
	public Gerente pesquisarGerentePorRestaurante(Restaurante restaurante) {
		return gerentes.pesquisarGerentePorRestaurante(restaurante);
	}

	public NegociosCliente getClientes() {
		return clientes;
	}

	public void setClientes(NegociosCliente clientes) {
		this.clientes = clientes;
	}

	public NegociosGerente getGerentes() {
		return gerentes;
	}

	public void setGerentes(NegociosGerente gerentes) {
		this.gerentes = gerentes;
	}

	
	public NegociosRestaurante getRestaurantes() {
		return restaurantes;
	}

	public void setRestaurantes(NegociosRestaurante restaurantes) {
		this.restaurantes = restaurantes;
	}
	
	// save and load
	public void saveData() throws Exception {
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream(filename);
			out = new ObjectOutputStream(fos);
			out.writeObject(this);
			
			out.close();
		} catch (Exception e) {
			throw e;
		}
	}
	
	public Delivery readData() throws Exception {
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream(filename);
			in = new ObjectInputStream(fis);
			Delivery objeto = (Delivery) in.readObject();
			in.close();
			return objeto;
		} catch (Exception e) {
			throw e;
		}
	}
	
}
