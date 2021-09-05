package dados;

import java.io.Serializable;
import java.util.ArrayList;

import negocios.Restaurante;

public class RepositorioRestaurantes implements Serializable {
	
	private ArrayList<Restaurante> repositorio;
	
	public RepositorioRestaurantes() {
		
		repositorio = new ArrayList<Restaurante>();
		
	}
	
	public void adicionar(Restaurante restaurante) {
		
		repositorio.add(restaurante);
		
		Restaurante added = repositorio.get(repositorio.size()-1);
		System.out.println("Restaurante "+added.getNome()+" com CNPJ "+added.getCnpj()+" adicionado");
		
	}
	
	public void remover(Restaurante restaurante) {
		
		repositorio.remove(restaurante);
		
	}
	
	public Restaurante consultarCnpj(String cnpj) {
		
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
	
	public ArrayList<Restaurante> restaurantesAbertos() {
		ArrayList<Restaurante> restaurantesAbertos = new ArrayList<Restaurante>();
		
		for (int i = 0; i < this.repositorio.size(); i++) {
			Restaurante aux = this.repositorio.get(i);
			System.out.println("Restaurante "+aux.getNome()+" aberto?"+aux.aberto);
			if (aux.estaAberto()) {
				restaurantesAbertos.add(aux);
			}
		}
		
		return restaurantesAbertos;
	}
	
	public int getQtdRestaurantes() {
		System.out.println("Quantidade de restaurantes cadastrados: "+this.repositorio.size());
		return this.repositorio.size();
	}

}
