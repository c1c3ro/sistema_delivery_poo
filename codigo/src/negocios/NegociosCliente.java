package negocios;

import dados.RepositorioClientes;

import java.util.ArrayList;
import java.util.Hashtable;

import Exceptions.ClienteJaExisteException;
import Exceptions.OpcaoInvalidaException;
import Exceptions.SemDinheiroException;
import Exceptions.UsuarioNaoEncontradoException;
import Exceptions.SacolaVaziaException;

public class NegociosCliente {
	
	RepositorioClientes repositorio;
	
	public NegociosCliente() {
		this.repositorio = new RepositorioClientes();
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
	
	public Hashtable<Restaurante, ArrayList<Item>> getItensNaSacola(Sacola sacola) throws SacolaVaziaException {
		if (sacola.getQtdItens() == 0) {
			throw new SacolaVaziaException("A sacola está vazia");
		}
		try {
			return sacola.getItens();
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

}