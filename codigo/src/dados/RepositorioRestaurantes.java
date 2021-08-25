package dados;

import java.util.ArrayList;

import negocios.Restaurante;

public class RepositorioRestaurantes {
	
	private ArrayList<Restaurante> repositorio;
	
	void adicionar(Restaurante restaurante) {
		
		repositorio.add(restaurante);
		
	}
	
	void remover(Restaurante restaurante) {
		
		repositorio.remove(restaurante);
		
	}
	
	Restaurante consultarCnpj(String cnpj) {
		
		Restaurante aux = null;
		
		for (int i = 0; i < repositorio.size(); i++) {
			aux = repositorio.get(i);
			if (aux.getCnpj().equals(cnpj)) {
				break;
			}
		}
		
		return aux;
		// se retornar null é porque o restaurante não foi encontrado
		
	}

}
