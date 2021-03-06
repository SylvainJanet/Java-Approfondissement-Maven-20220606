package o01.console;

import java.util.List;
import java.util.Scanner;

import classes.Cart;
import classes.CartLine;
import classes.Product;
import o01.console.tools.DisplayConsoleTools;
import o01.console.tools.InitialData;

public class App1 {

	private static List<Product> productList;
	private static Cart cart = new Cart();

	public static void main(String[] args) {
		productList = InitialData.productList;

		Scanner sc = new Scanner(System.in);
		int choice = 0;
		do {
			// afficher ma liste de produits
			DisplayConsoleTools.displayProducts(productList);

			// afficher les options
			// Menu
			// 1 - Ajouter au panier
			// 2 - Supprimer un produit du panier
			// 3 - Afficher le panier (? articles)
			// 4 - Quitter
			DisplayConsoleTools.displayMenu(cart);

			// demander le choix utilisateur
			System.out.println("Votre choix ?");
			choice = sc.nextInt(); // peut générer une erreur
			// -> faire une méthode pour s'assurer que l'utilisateur entre bien un nombre avec un try-catch
			switch (choice) {
			case 1:
				// ajouter produit
				addProduct(sc);
				break;
			case 2:
				// supprimer produit
				removeProduct(sc);
				break;
			case 3:
				// afficher panier
				DisplayConsoleTools.displayCart(cart);
				break;
			}

		} while (choice != 4);
		sc.close();

	}

	private static void removeProduct(Scanner sc) {
		System.out.println("Quel produit ?");
		int idProd = sc.nextInt();
		System.out.println("Combien ?");
		int qty = sc.nextInt();

		Product pToAdd = productList.get(idProd - 1);

		cart.removeLine(new CartLine(pToAdd, qty));

	}

	private static void addProduct(Scanner sc) {
		System.out.println("Quel produit ?");
		int idProd = sc.nextInt();
		System.out.println("Combien ?");
		int qty = sc.nextInt();

		Product pToAdd = productList.get(idProd - 1);

		cart.addLine(new CartLine(pToAdd, qty));

	}

}
