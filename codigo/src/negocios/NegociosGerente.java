package negocios;

import Exceptions.ClienteJaExisteException;
import Exceptions.UsuarioNaoEncontradoException;
import dados.RepositorioGerentes;

public class NegociosGerente {
	
	RepositorioGerentes repositorio;
	
	public NegociosGerente() {
		this.repositorio = new RepositorioGerentes();
	}
	
	public boolean gerenteExiste(String cpf) {
		Gerente busca = this.repositorio.consultar(cpf);
		if (busca != null) {
			System.out.println("Gerente " + busca.getNome() + " com CPF " + busca.getCPF() + " existe.");
			return true;
		} else {
			return false;
		}
	}
	
	public void cadastrarGerente(String nome, String cpf, String senha, String restauranteCnpj, String restauranteNome, String restauranteEndereco) throws ClienteJaExisteException {
		
		if (this.gerenteExiste(cpf)) {
			throw new ClienteJaExisteException("Gerente já existe!");
		} else {
			Restaurante novoRestaurante = new Restaurante(restauranteCnpj, restauranteNome);
			Gerente novoGerente = new Gerente(nome, cpf, senha, novoRestaurante);
			try {
				this.repositorio.adicionar(novoGerente);
			} catch (Exception e) {			
				throw e;
			}
		}
		
	}
	
	public boolean matchLoginSenha(String cpf, String senha) throws UsuarioNaoEncontradoException {
		if (!this.gerenteExiste(cpf)) {
			throw new UsuarioNaoEncontradoException("Gerente não encontrado");
		}
		
		Gerente cliente = this.repositorio.consultar(cpf);
		String pass = cliente.getSenha();
		
		if (senha.equals(pass)) {
			return true; //pode logar
		} else {
			return false; // usuario foi encontrado mas senha estava errada
		}
		
	}

}
