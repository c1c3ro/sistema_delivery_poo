package dados;

import java.util.ArrayList;

import negocios.Gerente;
import negocios.Restaurante;

public class RepositorioRestaurantes {
	
	private ArrayList<Restaurante> repositorio;
	
	public void adicionar(Restaurante restaurante) {
		
		repositorio.add(restaurante);
		
		Restaurante added = repositorio.get(repositorio.size()-1);
		System.out.println("Restaurante "+added.getNome()+" com CNPJ "+added.getCnpj()+" adicionado");
		
	}
	
	public void remover(Restaurante restaurante) {
		
		repositorio.remove(restaurante);
		
	}
	
	Restaurante consultarCnpj(String cnpj) {
		
		Restaurante aux = null;
		
		for (int i = 0; i < repositorio.size(); i++) {
			aux = repositorio.get(i);
			if (aux.getCnpj().equals(cnpj)) {
				return aux;
			}
		}
		
		return null;
		// se retornar null é porque o restaurante não foi encontrado
		
	}

}
