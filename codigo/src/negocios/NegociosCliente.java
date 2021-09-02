package negocios;

import dados.RepositorioClientes;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import Exceptions.ClienteJaExisteException;
import Exceptions.OpcaoInvalidaException;
import Exceptions.SemDinheiroException;
import Exceptions.UsuarioNaoEncontradoException;
import Exceptions.SacolaVaziaException;

public class NegociosCliente implements Serializable {
	
	RepositorioClientes repositorio;
	private String filename;
	
	public NegociosCliente() {
		this.repositorio = new RepositorioClientes();
		filename = "NegociosCliente.ser";
	}
	
	public boolean clienteExiste(String cpf) {
		Cliente busca = this.repositorio.consultar(cpf);
		if (busca != null) {
			System.out.println("Cliente " + busca.getNome() + " com CPF " + busca.getCPF() + " existe.");
			return true;
		} else {
			return false;
		}
	}
	
	public void cadastrarCliente(String nome, String cpf, String senha, String endereco) throws ClienteJaExisteException {
		
		if (this.clienteExiste(cpf)) {
			throw new ClienteJaExisteException("Cliente já existe!");
		} else {
			Cliente novoCliente = new Cliente(nome, cpf, senha, endereco);
			try {
				this.repositorio.adicionar(novoCliente);
			} catch (Exception e) {			
				throw e;
			}
		}
		
	}
	
	public Cliente matchLoginSenha(String cpf, String senha) throws UsuarioNaoEncontradoException {
		if (!this.clienteExiste(cpf)) {
			throw new UsuarioNaoEncontradoException("Cliente não encontrado");
		}
		
		Cliente cliente = this.repositorio.consultar(cpf);
		String pass = cliente.getSenha();
		
		if (senha.equals(pass)) {
			return cliente; //pode logar
		} else {
			return null; // usuario foi encontrado mas senha estava errada
		}
		
	}
	
	public void adicionarItemNaSacola(String cpf, Item item, Gerente gerente) throws UsuarioNaoEncontradoException {
		
		if (!this.clienteExiste(cpf)) {
			throw new UsuarioNaoEncontradoException("Cliente não encontrado");
		}
		
		Cliente cliente = this.repositorio.consultar(cpf);
		
		try {
			cliente.getSacola().adicionarItem(item, gerente);
		} catch(Exception e) {
			throw e;
		}
		
	}
	
	public void removerItemDaSacola(String cpf, Item item, Gerente gerente) throws UsuarioNaoEncontradoException {
		if (!this.clienteExiste(cpf)) {
			throw new UsuarioNaoEncontradoException("Cliente não encontrado");
		}
		
		Cliente cliente = this.repositorio.consultar(cpf);
		
		try {
			cliente.getSacola().removerItem(item, gerente);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public void esvaziarSacola(String cpf) throws UsuarioNaoEncontradoException {
		if (!this.clienteExiste(cpf)) {
			throw new UsuarioNaoEncontradoException("Cliente não encontrado");
		}
		
		Cliente cliente = this.repositorio.consultar(cpf);
		
		try {
			cliente.getSacola().esvaziarSacola();
		} catch (Exception e) {
			throw e;
		}
	}
	
	public ArrayList<Sacola> pedidosAntigos(String cpf) throws UsuarioNaoEncontradoException {
		if (!this.clienteExiste(cpf)) {
			throw new UsuarioNaoEncontradoException("Cliente não encontrado");
		}
		
		Cliente cliente = this.repositorio.consultar(cpf);
		
		try {
			return cliente.getPedidosAntigos();
		} catch (Exception e) {
			throw e;
		}
	}
	
	public Sacola pedidoAtivoMaisRecente(String cpf) throws UsuarioNaoEncontradoException {
		if (!this.clienteExiste(cpf)) {
			throw new UsuarioNaoEncontradoException("Cliente não encontrado");
		}
		
		Cliente cliente = this.repositorio.consultar(cpf);
		
		try {
			return cliente.pedidoAtivoMaisRecente();
			// retorna null se não houver
			// retorna o pedido se houver
		} catch (Exception e) {
			throw e;
		}
	}
	
	public int getStatusPedidos(String cpf) throws UsuarioNaoEncontradoException {
		if (!this.clienteExiste(cpf)) {
			throw new UsuarioNaoEncontradoException("Cliente não encontrado");
		}
		
		Cliente cliente = this.repositorio.consultar(cpf);
		
		try {
			return cliente.getSacola().getStatus();
		} catch (Exception e) {
			throw e;
		}
	}
	
	public Hashtable<Gerente, Integer> gerentesQueAutorizaram(String cpf) throws UsuarioNaoEncontradoException {
		if (!this.clienteExiste(cpf)) {
			throw new UsuarioNaoEncontradoException("Cliente não encontrado");
		}
		 	
		Cliente cliente = this.repositorio.consultar(cpf);
		
		try {
			return cliente.getSacola().aprovacoesGerentes();
		} catch (Exception e) {
			throw e;
		}
	}
	
	public double adicionarDinheiro(String cpf, double valor) throws UsuarioNaoEncontradoException {
		if (!this.clienteExiste(cpf)) {
			throw new UsuarioNaoEncontradoException("Cliente não encontrado");
		}
		
		Cliente cliente = this.repositorio.consultar(cpf);
		double carteira = -1.0;
		try {
			carteira = cliente.adicionarDinheiro(valor);
		} catch (Exception e) {
			throw e;
		}
		
		return carteira;
		// se retornar -1.0 tem algum problema
		// deve retornar o valor da carteira atualizado
	}
	
	public double realizarCompra(String cpf) throws UsuarioNaoEncontradoException, SemDinheiroException {
		if (!this.clienteExiste(cpf)) {
			throw new UsuarioNaoEncontradoException("Cliente não encontrado");
		}
		
		try {
		
			Cliente cliente = this.repositorio.consultar(cpf);
			double carteira = cliente.getCarteira();
			Sacola clienteSacola = cliente.getSacola();
			double valor = clienteSacola.getTotal();
			clienteSacola.enviarPedidosParaAprovacao();
			
			if (valor > carteira) {
				throw new SemDinheiroException("Sem dinheiro, irmão");
			} else {
				return cliente.fazerPedido(valor);
			}			
			
		} catch (Exception e) {
			throw e;
		}
	}
	
	public String atualizarCliente(String cpf, int campo, String novoValor) throws UsuarioNaoEncontradoException, OpcaoInvalidaException {
		
		Cliente paraAtualizar = this.repositorio.consultar(cpf);
		
		if (paraAtualizar == null) {
			throw new UsuarioNaoEncontradoException("Cliente não encontrado");
		}
		
		try {
			return this.repositorio.atualizar(paraAtualizar, campo, novoValor);
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	public Sacola getSacolaAtual(String cpf) throws UsuarioNaoEncontradoException {
		
		Cliente cliente = this.repositorio.consultar(cpf);
		
		if (cliente == null) {
			throw new UsuarioNaoEncontradoException("Cliente não encontrado");
		}
		
		try {
			return cliente.getSacola();
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	public Hashtable<Restaurante, ArrayList<Item>> getItensNaSacolaPorRestaurante(Sacola sacola) throws SacolaVaziaException {
		if (sacola.getQtdItens() == 0) {
			throw new SacolaVaziaException("A sacola está vazia");
		}
		try {
			return sacola.getItens();
		} catch (Exception e) {
			throw e;
		}
	}
	
	public ArrayList<Item> getItensNaSacola(Sacola sacola) throws SacolaVaziaException {
		if (sacola.getQtdItens() == 0) {
			throw new SacolaVaziaException("A sacola está vazia");
		}
		try {
			Hashtable<Restaurante, ArrayList<Item>> itensPorRestaurante = sacola.getItens();
			if (itensPorRestaurante.size() == 0) {
				throw new SacolaVaziaException("itensPorRestaurante está vazio");
			}
			ArrayList<Item> itensNaSacola = new ArrayList<Item>();
			Enumeration<Restaurante> restaurantes = itensPorRestaurante.keys();
			Restaurante aux = null;
			while (restaurantes.hasMoreElements()) {
				aux = restaurantes.nextElement();
				ArrayList<Item> itens = itensPorRestaurante.get(aux);
				for (int i = 0; i < itens.size(); i++) {
					itensNaSacola.add(itens.get(i));
				}
			}
			return itensNaSacola;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public double getTotalSacola(Sacola sacola) throws SacolaVaziaException {
		if (sacola.getQtdItens() == 0) {
			throw new SacolaVaziaException("A sacola está vazia");
		}
		try {
			return sacola.getTotal();
		} catch (Exception e) {
			throw e;
		}
	}
	
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
	
	public NegociosCliente readData() throws Exception {
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream(filename);
			in = new ObjectInputStream(fis);
			NegociosCliente objeto = (NegociosCliente) in.readObject();
			in.close();
			return objeto;
		} catch (Exception e) {
			throw e;
		}
	}

}