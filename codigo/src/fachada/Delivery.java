package fachada;
import Exceptions.ClienteJaExisteException;
import Exceptions.UsuarioNaoEncontradoException;
import negocios.*;

public class Delivery {
	
	private NegociosCliente clientes;
	private NegociosGerente gerentes;
	private NegociosRestaurante restaurantes = null;
	
	public Delivery() {
		this.clientes = new NegociosCliente();
		this.gerentes = new NegociosGerente();
	}
	
	public void cadastrarCliente(String nome, String cpf, String senha, String endereco) throws ClienteJaExisteException {
		clientes.cadastrarCliente(nome, cpf, senha, endereco);
	}
	
	public boolean matchLoginSenhaCliente(String cpf, String senha) throws UsuarioNaoEncontradoException {
		return clientes.matchLoginSenha(cpf, senha);
	}
	
	public void cadastrarGerente(String nome, String cpf, String senha, String restauranteCnpj, String restauranteNome, NegociosRestaurante negocioRestaurante) throws ClienteJaExisteException {
		gerentes.cadastrarGerente(nome, cpf, senha, restauranteCnpj, restauranteNome, negocioRestaurante);
	}
	
	public boolean matchLoginSenhaGerente(String cpf, String senha) throws UsuarioNaoEncontradoException {
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

	public NegociosRestaurante getRestaurantes() {
		return restaurantes;
	}

	public void setRestaurantes(NegociosRestaurante restaurantes) {
		this.restaurantes = restaurantes;
	}
	
}
