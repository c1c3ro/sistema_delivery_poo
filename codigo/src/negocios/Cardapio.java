package negocios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javafx.util.Pair;

public class Cardapio {
	
	public ArrayList<String> categorias;
	
	//Hashmap < inteiro: indice da categoria
	//			inteiro: id do item
	//			String: nome do item >
	Map<Pair<Integer, Integer>, String> itensID; 

}
