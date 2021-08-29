package negocios;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

public class Cardapio {
	
	public Hashtable<Integer, Item> itensID; 
	
	public Cardapio() {
		itensID = new Hashtable<Integer, Item>();
	}
	
	public void adicionarItem(Item item) {
		itensID.put(item.getID(), item);
		System.out.println("Quantidade de itens no cardapio: "+itensID.size());
	}
	
	public void removerItem(Item item) {
		int ID = item.getID();
		itensID.remove(ID);
	}
	
	public Item getItem(int ID) {
		return itensID.get(ID);
	}
	
	public boolean itemExiste(Item item) {
		
		Item aux = null;
		
		Enumeration<Integer> IDs = itensID.keys();
		while (IDs.hasMoreElements()) {
			int id = IDs.nextElement();
			
			aux = itensID.get(id);
			if (aux.equals(item)) {
				System.out.println("Item "+aux.getNome()+" com preço "+aux.getValor()+" existe no cardápio");
				return true;
			}
		}
		
		return false;
		
	}
	
	public ArrayList<String> getCategorias() {
		
		// retornas as categorias únicas que existem no cardapio
		
		Item aux;
		String categoria;
		ArrayList<String> categoriasUnicas = new ArrayList<String>();
		Enumeration<Integer> IDs = itensID.keys();
		while (IDs.hasMoreElements()) {
			int id = IDs.nextElement();
			
			aux = itensID.get(id);
			categoria = aux.getCategoria();
			
			if (!categoriasUnicas.contains(categoria)) {
				categoriasUnicas.add(categoria);
			}
		}
		
		return categoriasUnicas;
		
	}
	
	public Hashtable<String, ArrayList<Item>> itensPorCategoria(){
		
		// precisa ver o que tem de errado nisso aqui e corrigir todos os outros que tiverem
		// o mesmo problema com a Enumeration
		
		Item aux;
		String categoria;
		
		Hashtable<String, ArrayList<Item>> itensPorCategoria = new Hashtable<String, ArrayList<Item>>();
		
		Enumeration<Integer> IDs = itensID.keys();
		System.out.println("Tamanho itensID :"+itensID.size());
		System.out.println(IDs);
		
		while (IDs.hasMoreElements()) {
			int id = IDs.nextElement();
			
			aux = itensID.get(id);
			categoria = aux.getCategoria();
			System.out.println("Categoria "+categoria);
			
			if(!itensPorCategoria.containsKey(categoria)) {
				ArrayList<Item> categItens = new ArrayList<Item>();
				categItens.add(aux);
				itensPorCategoria.put(categoria, categItens);
			} else {
				itensPorCategoria.get(categoria).add(aux);
			}
		}
		
		System.out.println("Tamanhos de itens por Categoria: "+itensPorCategoria.size());
		return itensPorCategoria;
		
		
	}
	
	public Hashtable<Integer, Item> itensPorID(){
		System.out.println("Quantidade de itens no cardapio: "+itensID.size());
		return itensID;
	}

}
