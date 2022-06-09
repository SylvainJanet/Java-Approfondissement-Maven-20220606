package o01.console.tools;

import java.util.ArrayList;
import java.util.List;

import classes.Product;

public class InitialData {

	public static List<Product> productList;
	
	/*
	 * Un bloc static peut être utilisé pour initialiser une
	 * classe. Ce code est exécuté une seule fois : la
	 * première fois que la classe est chargée en mémoire
	 */
	
	static {
		productList = new ArrayList<>();
		productList.add(new Product(1,"Ordinateur",500));
		productList.add(new Product(2,"Clavier",50));
		productList.add(new Product(3,"Souris",20));
	}
}
