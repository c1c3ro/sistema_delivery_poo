package dados;

import java.util.ArrayList;

import negocios.Cliente;

public class RepositorioClientes implements RepositorioUsuarios<Cliente> {
	
	private ArrayList<Cliente> repositorio;
	
	public RepositorioClientes() {
		
		repositorio = new ArrayList<Cliente>();
		
	}

	@Override
	public void adicionar(Cliente usuario) {
		
		repositorio.add(usuario);
				
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
