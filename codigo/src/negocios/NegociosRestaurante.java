package negocios;

import java.util.ArrayList;
import java.util.Hashtable;

import Exceptions.ClienteJaExisteException;
import Exceptions.UsuarioNaoEncontradoException;
import dados.RepositorioRestaurantes;

public class NegociosRestaurante {
	
	public RepositorioRestaurantes repositorio;
	
	public NegociosRestaurante() {
		
		repositorio = new RepositorioRestaurantes();
		
	}
	
	public boolean restauranteExiste(String cnpj) {
		Restaurante busca = this.repositorio.consultarCnpj(cnpj);
		if (busca != null) {
			System.out.println("Restaurante " + busca.getNome() + " com CNPJ " + busca.getCnpj() + " existe.");
			return true;
		} else {
			return false;
		}
	}
	
	public void cadastrarRestaurante(Restaurante novoRestaurante) throws ClienteJaExisteException {
		
		if (this.restauranteExiste(novoRestaurante.getCnpj())) {
			throw new ClienteJaExisteException("Restaurante j� existe!");
		} else {
			try {
				this.repositorio.adicionar(novoRestaurante);
			} catch (Exception e) {			
				throw e;
			}
		}
		
	}
	
	public void inserirProdutoNoCardapio(Restaurante restaurante, String nome, double valor, String categoria, String descricao) throws ClienteJaExisteException, UsuarioNaoEncontradoException {
		
		if (!this.restauranteExiste(restaurante.getCnpj())) {
			throw new UsuarioNaoEncontradoException("Restaurante n�o existe!");
		}
		
		Item novoItem = new Item(nome, valor, categoria, descricao);
		
		if (restaurante.getCardapio().itemExiste(novoItem)) {
			throw new ClienteJaExisteException("Item j� existe no card�pio do restaurante");
		} else {
			try {
				Cardapio restauranteCardapio = restaurante.getCardapio();
				restauranteCardapio.adicionarItem(novoItem);
			} catch (Exception e) {
				throw e;
			}
		}
		
		
	}
	
	public void removerProdutoDoCardapio(Restaurante restaurante, int ID) throws UsuarioNaoEncontradoException {
		
		if (!this.restauranteExiste(restaurante.getCnpj())) {
			throw new UsuarioNaoEncontradoException("Restaurante n�o existe!");
		}
		
		Item itemParaRemover = null;
		
		try {
			Cardapio restauranteCardapio = restaurante.getCardapio();
			itemParaRemover = restauranteCardapio.getItem(ID);
			restauranteCardapio.removerItem(itemParaRemover);
		} catch (Exception e) {
			throw e;
		}		
		
	}
	
	public ArrayList<Restaurante> restaurantesAbertos() {
		
		if (repositorio.getQtdRestaurantes() > 0) {
			return repositorio.restaurantesAbertos();
		}
		
		return null;
		// se retornar null n�o tem nenhum restaurante aberto ou n�o tem nenhum restaurante cadastrado
	}
	
	public Hashtable<Integer, Item> getItensDoCardapio(Restaurante restaurante) throws UsuarioNaoEncontradoException {
		
		if (!this.restauranteExiste(restaurante.getCnpj())) {
			throw new UsuarioNaoEncontradoException("Restaurante n�o existe!");
		}
		
		try {
			
			return restaurante.getCardapio().itensPorID();
		
		} catch (Exception e) {
			
			throw e;
			
		}
				
	}
	
	public ArrayList<String> getCategorias(String cnpj) throws UsuarioNaoEncontradoException {
		
		if (!this.restauranteExiste(cnpj)) {
			throw new UsuarioNaoEncontradoException("Restaurante n�o existe!");
		}
		
		Restaurante restaurante = repositorio.consultarCnpj(cnpj);
		
		return restaurante.getCardapio().getCategorias();	
		
	}
	
	public double ganhos(String cnpj) throws UsuarioNaoEncontradoException {
		if (!this.restauranteExiste(cnpj)) {
			throw new UsuarioNaoEncontradoException("Restaurante n�o existe!");
		}
		
		Restaurante restaurante = repositorio.consultarCnpj(cnpj);
		
		return restaurante.getReceita();
	}

}
