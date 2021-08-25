package dados;

import java.util.ArrayList;

import negocios.Cliente;

public class RepositorioClientes implements RepositorioUsuarios<Cliente> {
	
	private ArrayList<Cliente> repositorio;
	
	public RepositorioClientes() {
		
		repositorio = new ArrayList<Cliente>();
		
	}

	@Override
	public boolean adicionar(Cliente usuario) {
		
		repositorio.add(usuario);
		
		if (repositorio.get(repositorio.size()-1).equals(usuario)) {
			return true;
		} else {
			return false;
		}
		
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
				break;
			}
		}
		
		return aux;
		// se retornar null é porque o cliente não foi encontrado
		// se retornar uma instancia de Cliente(), o cliente foi encontrado
	}

	@Override
	public void atualizar(Cliente usuario) {
		
		
		
	}

	
	
}
