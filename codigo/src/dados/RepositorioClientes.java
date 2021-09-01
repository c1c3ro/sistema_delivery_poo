package dados;

import java.util.ArrayList;

import Exceptions.OpcaoInvalidaException;

import negocios.Cliente;

public class RepositorioClientes implements RepositorioUsuarios<Cliente> {
	
	private ArrayList<Cliente> repositorio;
	
	public RepositorioClientes() {
		
		repositorio = new ArrayList<Cliente>();
		
	}

	@Override
	public void adicionar(Cliente usuario) {
		
		repositorio.add(usuario);
		
		Cliente added = repositorio.get(repositorio.size()-1);
		System.out.println("Cliente "+added.getNome()+" com CPF "+added.getCPF()+" adicionado");
		
	}

	@Override
	public void remover(Cliente usuario) {
		
		repositorio.remove(usuario);
		
	}

	@Override
	public Cliente consultar(String cpf) {
		
		Cliente aux = null;
		
		for (int i = 0; i < repositorio.size(); i++) {
			aux = repositorio.get(i);
			if (aux.getCPF().equals(cpf)) {
				return aux;
			}
		}
		
		return null;
		// se retornar null é porque o cliente não foi encontrado
		// se retornar uma instancia de Cliente(), o cliente foi encontrado
	}

	@Override
	public String atualizar(Cliente usuario, int campo, String novoValor) throws OpcaoInvalidaException {
		
		if (campo == 1) {
			//nome
			usuario.atualizarNome(novoValor);
			return usuario.getNome();
		} else if (campo == 2) {
			//senha
			usuario.atualizarSenha(novoValor);
			return usuario.getSenha();
		} else if (campo == 3) {
			//endereco
			usuario.atualizarEndereco(novoValor);
			return usuario.getEndereco();
		} else {
			throw new OpcaoInvalidaException("Opção digitada inválida");
		}
		
	}

	
	
}
