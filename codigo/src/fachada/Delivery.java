package fachada;
import Exceptions.ClienteJaExisteException;
import Exceptions.ErroDesconhecidoNoCadastro;
import Exceptions.UsuarioNaoEncontradoException;
import negocios.NegociosCliente;
//import negocios.NegociosGerente;

public class Delivery {
	
	private NegociosCliente clientes;
	//private NegociosGerente gerentes;
	
	public Delivery() {
		this.clientes = new NegociosCliente();
		//this.gerentes = new NegociosGerente();
	}
	
	public boolean clienteExiste(String cpf) {
		return clientes.clienteExiste(cpf);
	}
	
	public void cadastrarCliente(String nome, String cpf, String senha, String endereco) throws ClienteJaExisteException, ErroDesconhecidoNoCadastro {
		clientes.cadastrarCliente(nome, cpf, senha, endereco);
	}
	
	public boolean matchLoginSenha(String cpf, String senha) throws UsuarioNaoEncontradoException {
		return clientes.matchLoginSenha(cpf, senha);
	}
	
}
