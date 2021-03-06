package o09.reflexion;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import classes.Personne;
import classes.Product;
import o01.console.tools.InitialData;
import o07.genericite.GenericFunctions;

public class App9 {

	public static void main(String[] args) {

		/*
		 * 
		 * La réflexion est un concept de POO qui permet d'obtenir des informations
		 * structurelles sur un type de données. Par exemple, connaître les attributs
		 * 
		 * Ces informations sont stockées dans un objet de type Class. Ce type "Class"
		 * est générique, il fonctionne pour n'importe quel type d'objet
		 * 
		 * Il utilise java.lang
		 * 
		 */

		System.out.println("_____ getDeclaredFields _____");
		Class<Product> metadata = Product.class;

		Product product = new Product(1, "Ma description", 50);

		Field[] fields = metadata.getDeclaredFields();
		for (Field field : fields) {
			System.out.println("_____");
			System.out.println(field);
			System.out.println(field.getName());

			try {
				field.setAccessible(true);
				// par défaut lorsqu'on récupère les champs d'une classe, un flag accessible
				// et mis à false. Il a le sens suivant :
				// field Accessible false : les champs privés ne seront pas accessibles,
				// les champs publics seront accessibles, les champs protected ne seront
				// accessibles que
				// depuis les classes qui héritent de la classe en question. Autrement dit les
				// vérifications d'accessibilité seront effectuées
				// field Accessible true : aucune vérification d'accessibilité ne se fera dans
				// l'objet field
				System.out.println(field.get(product)); // récupérer la valeur du champ d'un certain objet
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}

		System.out.println("_____ createInstance _____");
		try {
			Product p = metadata.newInstance();
			System.out.println(p);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}

		System.out.println("_____ getSimpleName _____");
		System.out.println("Simple name : " + metadata.getSimpleName());
		System.out.println("Name : " + metadata.getName());
		// si vous utilisez une interface au lieu d'une classe, avec de l'héritage,
		// les noms suivants seront différents
		System.out.println("Type name : " + metadata.getTypeName());
		System.out.println("Canonical name : " + metadata.getCanonicalName());

		System.out.println("_____ CsvToolsGen.toCsv _____");
		
		try {
			List<Product> productList = InitialData.productList;
			CsvToolsGen.toCsv("productGen.csv", productList, ";", Product.class);
			
			List<Personne> personneList = new ArrayList<>(
					Arrays.asList(new Personne(1, "John", "Doe", 45), new Personne(2, "Truc", "Bidule", 20)));
			CsvToolsGen.toCsv("personneGen.csv", personneList, ";", Personne.class);
		} catch (IOException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}

		

		System.out.println("_____ CsvToolsGen.fromCsv _____");

		try {
			List<Product> productList2 = CsvToolsGen.fromCsv("productGen2.csv", ";", Product.class);
			List<Personne> personneList2 = CsvToolsGen.fromCsv("personneGen2.csv", ";", Personne.class);

			GenericFunctions.displayList(productList2);
			GenericFunctions.displayList(personneList2);
		} catch (InstantiationException | IllegalAccessException | IOException e) {
			e.printStackTrace();
		}
		
	}
}
