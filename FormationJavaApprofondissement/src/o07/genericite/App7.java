package o07.genericite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App7 {

	public static void main(String[] args) {

		/*
		 * 
		 * En POO, la généricité est un concept qui permet de définir des algos (des
		 * classes, des méthodes, des interfaces,...) en prenant en paramètre un type
		 * d'objet.
		 * 
		 * En Java, il y a énormément de classes génériques déjà existantes, par exemple
		 * les collections
		 * 
		 */

		List<String> collection = new ArrayList<String>();

		/*
		 *
		 * List est une interface générique ArrayList est une classe générique qui
		 * implémente l'interface générique List
		 * 
		 * Lors de l'instanciation, on spécifie le type paramétré grâce aux < >
		 * L'opérateur diamant : lorsque le paramètre de la classe qui implémente
		 * l'interface est le même que le paramètre de l'interface, on a pas besoin de
		 * le répéter
		 * 
		 */

		List<String> collection2 = new ArrayList<>();

		List<Integer> collection3 = new ArrayList<>(Arrays.asList(3, 5, 4));
		// je peux définir une liste avec des valeurs initiales en passant les données
		// dans le constructeur

		System.out.println("_____ displayList _____");

		GenericFunctions.displayList(collection);
		GenericFunctions.displayList(collection2);
		GenericFunctions.displayList(collection3);

		System.out.println("_____ fromArrayToList _____");

		String[] strArray = new String[] { "chaine de char 1", "chaine de char 2" };
		Integer[] intArray = new Integer[] { 3, 42, 125 };
		List<String> strList = new ArrayList<>();
		List<Integer> intList = new ArrayList<>();

		GenericFunctions.fromArrayToList(strArray, strList);
		GenericFunctions.fromArrayToList(intArray, intList);

		GenericFunctions.displayList(strList);
		GenericFunctions.displayList(intList);
		
		System.out.println("_____ getFirstElement _____");
		
		System.out.println(GenericFunctions.getFirstElement(strList));
		System.out.println(GenericFunctions.getFirstElement(intList));

		System.out.println("_____ GenericClass _____");
		
		GenericClass<String> strGenClass = new GenericClass<>();
		GenericClass<Integer> intGenClass = new GenericClass<>(new ArrayList<>(),5,10);
		
		System.out.println(strGenClass.getElement());
		System.out.println(intGenClass.getElement());
		
		// architecture type du DAO en utilisant les génériques :
		// regarder o07.genericite.interfacegen
		// problème : on doit toujours implémenter des fonctions qui se ressemblent beaucoup
		// qui correspondent à l'implémentation de IGenericDao
		// la logique de chaque méthode est la même quelle que soit l'entité en question
		// on va voir des outils (reflexion) qui nous permettront de faire l'implémentation de manière
		// générique
		// puis regarder o07.genericite.classegen
		
		
		// Il y a plusieurs types d'architecture, on peut parler de l'architecture en 3 couches
		// couche modèle : le modèle objet, ce sont les classes dont les instances sont les entités
		// en base de données
		// couche dao : l'accès à la base de données, il s'agit de classes dont les instances nous
		// permettent de faire les opérations fondamentales en base de données
		// Ce sont les repositories
		// couche métier : elle est consitutée de classes dont les instances nous permettent de
		// répondre à des problématiques métier
		// Ce sont les services
		
	}

}
