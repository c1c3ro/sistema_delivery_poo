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
	}
	
	public void removerItem(Item item) {
		int ID = item.getID();
		itensID.remove(ID);
	}
	
	public Item getItem(int ID) {
		return itensID.get(ID);
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
		
		Item aux;
		String categoria;
		
		Hashtable<String, ArrayList<Item>> itensPorCategoria = new Hashtable<String, ArrayList<Item>>();
		
		Enumeration<Integer> IDs = itensID.keys();
		
		while (IDs.hasMoreElements()) {
			int id = IDs.nextElement();
			
			aux = itensID.get(id);
			categoria = aux.getCategoria();
			
			if(!itensPorCategoria.containsKey(categoria)) {
				ArrayList<Item> categItens = new ArrayList<Item>();
				categItens.add(aux);
				itensPorCategoria.put(categoria, categItens);
			} else {
				itensPorCategoria.get(categoria).add(aux);
			}
		}
		
		return itensPorCategoria;
		
		
	}

}
