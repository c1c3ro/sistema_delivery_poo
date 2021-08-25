package negocios;

import java.util.ArrayList;

public class Sacola {
	
	public ArrayList<Item> itens;
	public double total;
	
	public Sacola() {
		itens = new ArrayList<Item>();
		total = 0.0;
	}
	
	public void adicionarItem(Item item) {
		itens.add(item);
	}
	
	public void removerItem(Item item) {
		itens.remove(item);		
	}
	
	public ArrayList<Item> getItens() {
		return itens;
	}
	
	public double getTotal() {
		return total;
	}

}
