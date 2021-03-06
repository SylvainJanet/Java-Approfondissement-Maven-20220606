package o07.genericite;

import java.util.List;

public class GenericFunctions {

	public static void displayList(List<?> liste) {
		// ? sur un argument de type générique : ça peut être n'importe quoi
		// avantage : on peut utiliser n'importe quelle liste
		// inconvénient : on va traiter tous les objets de la liste comme des instances
		// d'objets

		if (liste.size() == 0) {
			System.out.println("Liste vide");
		} else {
			for (Object obj : liste) {
				System.out.println(obj);
			}
		}
	}
	
	// objectif : mettre toutes les valeurs d'un tableau dans une liste
	// deux problèmes pour l'utilisation du ? : 
	// premier : on ne veut pas forcément mettre en paramètre un type qui est utilisé
	// comme argument de type générique -> le ? ne fonctionnera pas
	// deuxième : on veut parfois mettre des conditions sur le type, ou l'utiliser d'une
	// certaine manière (ici, on souhaite le même paramètre de type pour le tableau et pour
	// la liste)
	
	// pour définir une méthode générique, on doit mettre entre < > le ou les types qu'on
	// souhaite paramétrer et on leur donne un nom, une seule lettre en majuscule.
	public static <T> void fromArrayToList(T[] array, List<T> list) {
		for (T obj : array) {
			list.add(obj);
		}
	}
	
	// on peut utiliser ces arguments de type dans les types de valeurs de retour
	public static <T> T getFirstElement(List<T> list) {
		if (list.size() == 0) {
			return null;
		} 
		return list.get(0);
	}
}
