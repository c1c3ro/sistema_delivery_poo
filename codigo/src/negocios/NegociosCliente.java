package negocios;

import dados.RepositorioClientes;
import Exceptions.ClienteJaExisteException;
import Exceptions.SemDinheiroException;
import Exceptions.UsuarioNaoEncontradoException;

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
			double valor = cliente.getSacola().getTotal();
			
			if (valor > carteira) {
				throw new SemDinheiroException("Sem dinheiro, irmão");
			} else {
				return cliente.fazerPedido(valor);
			}			
			
		} catch (Exception e) {
			throw e;
		}
	}

}