package dados;

import java.util.ArrayList;

import negocios.Gerente;

public class RepositorioGerentes implements RepositorioUsuarios<Gerente> {
	
	private ArrayList<Gerente> repositorio;
	
	public RepositorioGerentes() {
		
		repositorio = new ArrayList<Gerente>();
		
	}

	@Override
	public void adicionar(Gerente usuario) {
		
		repositorio.add(usuario);
		
	}

	@Override
	public void remover(Gerente usuario) {
		
		repositorio.remove(usuario);
		
	}

	@Override
	public Gerente consultar(String cpf) {
		
		Gerente aux = null;
		
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
	public void atualizar(Gerente usuario) {		
		
		
	}

	
	
}
