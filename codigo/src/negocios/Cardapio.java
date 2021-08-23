package negocios;

import java.util.ArrayList;
import java.util.Hashtable;

public class Cardapio {
	
	public ArrayList<String> categorias;
	
	//Hashmap < inteiro: indice da categoria
	//			inteiro: id do item
	//			String: nome do item >
	Hashtable<Integer, Item> itensID; 

}
