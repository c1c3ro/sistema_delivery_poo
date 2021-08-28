package dados;

import java.util.ArrayList;

import negocios.Cliente;
import negocios.Gerente;
import negocios.Restaurante;

public class RepositorioGerentes implements RepositorioUsuarios<Gerente> {
	
	private ArrayList<Gerente> repositorio;
	
	public RepositorioGerentes() {
		
		repositorio = new ArrayList<Gerente>();
		
	}

	@Override
	public void adicionar(Gerente usuario) {
		
		repositorio.add(usuario);
		
		Gerente added = repositorio.get(repositorio.size()-1);
		System.out.println("Gerente "+added.getNome()+" com CPF "+added.getCPF()+" adicionado");
		
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
				return aux;
			}
		}
		
		return null;
		// se retornar null é porque o cliente não foi encontrado
		// se retornar uma instancia de Cliente(), o cliente foi encontrado
	}

	@Override
	public void atualizar(Gerente usuario) {		
		
		
	}
	
	public Gerente gerentePorRestaurante(Restaurante restaurante) {
		Gerente aux;
		for (int i = 0; i < repositorio.size(); i++) {
			aux = repositorio.get(i);
			Restaurante restauranteGerente = aux.getRestaurante();
			if (restauranteGerente.equals(restaurante)) {
				return aux;
			}
		}
		return null;
	}

	
	
}
