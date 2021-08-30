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
	
	public void cadastrarGerente(String nome, String cpf, String senha, String restauranteCnpj, String restauranteNome, NegociosRestaurante negocioRestaurante) throws ClienteJaExisteException {
		
		if (this.gerenteExiste(cpf)) {
			throw new ClienteJaExisteException("Gerente já existe!");
		} else {
			Restaurante novoRestaurante = new Restaurante(restauranteCnpj, restauranteNome);
			negocioRestaurante.cadastrarRestaurante(novoRestaurante);
			Gerente novoGerente = new Gerente(nome, cpf, senha, novoRestaurante);
			try {
				this.repositorio.adicionar(novoGerente);
			} catch (Exception e) {			
				throw e;
			}
		}
		
	}
	
	public Gerente matchLoginSenha(String cpf, String senha) throws UsuarioNaoEncontradoException {
		if (!this.gerenteExiste(cpf)) {
			throw new UsuarioNaoEncontradoException("Gerente não encontrado");
		}
		
		Gerente gerente = this.repositorio.consultar(cpf);
		String pass = gerente.getSenha();
		
		if (senha.equals(pass)) {
			return gerente; //pode logar
		} else {
			return null; // usuario foi encontrado mas senha estava errada
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
			System.out.println("Restaurante "+gerente.getRestaurante().getNome()+" está aberto? "+gerente.getRestaurante().aberto);
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	public void fecharRestaurante(Gerente gerente) {
		
		try {
			gerente.getRestaurante().fechar();
			System.out.println("Restaurante "+gerente.getRestaurante().getNome()+" está aberto? "+gerente.getRestaurante().aberto);
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	public boolean aprovarPedido(Gerente gerente, Sacola sacola) throws UsuarioNaoEncontradoException {
			
		try {
			int aprovarPedido = gerente.aprovarPedido(sacola);
			
			if (aprovarPedido == -1) {
				throw new UsuarioNaoEncontradoException("Gerente não encontrado!");
			} else if (aprovarPedido == 1) {
				return true;
			} else {
				return false;
			}
			
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	public Gerente pesquisarGerentePorRestaurante(Restaurante restaurante) {
		
		try {
			return this.repositorio.gerentePorRestaurante(restaurante);
			// retorna null se não encontrou nenhum gerente com tal restaurante
			// retorna o gerente se encontrar
		} catch (Exception e) {
			throw e;
		}
		
	}

}
