package negocios;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;

import Exceptions.ClienteJaExisteException;
import Exceptions.OpcaoInvalidaException;
import Exceptions.UsuarioNaoEncontradoException;
import dados.RepositorioGerentes;

public class NegociosGerente implements Serializable {
	
	RepositorioGerentes repositorio;
	private String filename;
	
	public NegociosGerente() {
		this.repositorio = new RepositorioGerentes();
		this.filename = "NegociosGerente.ser";
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
	
	public Hashtable<Sacola, ArrayList<Item>> getPedidosParaAprovacao(Gerente gerente) throws UsuarioNaoEncontradoException {
		if (!this.gerenteExiste(gerente.getCPF())) {
			throw new UsuarioNaoEncontradoException("Gerente não encontrado!");
		}
		try {
			return gerente.getPedidosParaAprovacao();
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
	
	public String atualizarGerente(String cpf, int campo, String novoValor) throws UsuarioNaoEncontradoException, OpcaoInvalidaException {
		
		Gerente paraAtualizar = this.repositorio.consultar(cpf);
		
		if (paraAtualizar == null) {
			throw new UsuarioNaoEncontradoException("Cliente não encontrado");
		}
		
		try {
			return this.repositorio.atualizar(paraAtualizar, campo, novoValor);
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
	
	public NegociosGerente readData() throws Exception {
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream(filename);
			in = new ObjectInputStream(fis);
			NegociosGerente objeto = (NegociosGerente) in.readObject();
			in.close();
			return objeto;
		} catch (Exception e) {
			throw e;
		}
	}

}
