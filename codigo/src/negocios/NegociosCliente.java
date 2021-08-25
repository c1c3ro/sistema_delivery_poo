package negocios;

import dados.RepositorioClientes;
import Exceptions.ClienteJaExisteException;
import Exceptions.ErroDesconhecidoNoCadastro;
import Exceptions.UsuarioNaoEncontradoException;

public class NegociosCliente {
	
	RepositorioClientes repositorio;
	
	public NegociosCliente() {
		this.repositorio = new RepositorioClientes();
	}
	
	public boolean clienteExiste(String cpf) {
		Cliente busca = this.repositorio.consultar(cpf);
		if (busca != null) {
			System.out.println("Cliente " + busca.getNome() + " com CPF " + busca.getCPF() + " já existe.");
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
	
	public boolean matchLoginSenha(String cpf, String senha) throws UsuarioNaoEncontradoException {
		if (!this.clienteExiste(cpf)) {
			throw new UsuarioNaoEncontradoException("Usuário não encontrado");
		}
		
		Cliente cliente = this.repositorio.consultar(cpf);
		String pass = cliente.getSenha();
		
		if (senha.equals(pass)) {
			return true; //pode logar
		} else {
			return false; // usuario foi encontrado mas senha estava errada
		}
		
	}

}