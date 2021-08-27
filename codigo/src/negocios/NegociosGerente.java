package negocios;

import Exceptions.ClienteJaExisteException;
import Exceptions.UsuarioNaoEncontradoException;
import dados.RepositorioGerentes;
import dados.RepositorioRestaurantes;

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
	
	public void cadastrarGerente(String nome, String cpf, String senha, String restauranteCnpj, String restauranteNome, NegociosRestaurante negocioRestaurante) throws ClienteJaExisteException {
		
		if (this.gerenteExiste(cpf)) {
			throw new ClienteJaExisteException("Gerente já existe!");
		} else {
			Restaurante novoRestaurante = new Restaurante(restauranteCnpj, restauranteNome);
			negocioRestaurante.cadastrarRestaurante(restauranteCnpj, restauranteNome);
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
	
	public Restaurante getRestaurante(String cpfDoGerente) throws UsuarioNaoEncontradoException {
		
		if (!this.gerenteExiste(cpfDoGerente)) {
			throw new UsuarioNaoEncontradoException("Gerente não encontrado!");
		}
		
		Gerente gerente = repositorio.consultar(cpfDoGerente);
		
		return gerente.getRestaurante();
		
	}
	
	public void abrirRestaurante(Gerente gerente) {
		
		try {
			gerente.getRestaurante().abrir();
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	public void fecharRestaurante(Gerente gerente) {
		
		try {
			gerente.getRestaurante().fechar();
		} catch (Exception e) {
			throw e;
		}
		
	}

}
