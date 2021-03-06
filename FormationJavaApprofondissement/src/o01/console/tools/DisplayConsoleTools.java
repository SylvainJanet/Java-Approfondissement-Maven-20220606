package o01.console.tools;

import java.util.List;

import classes.Cart;
import classes.Product;

public class DisplayConsoleTools {

	public static void displayProducts(List<Product> productList) {
		int i = 1;
		for (Product p : productList) {
			System.out.println(i + " - " + p.getDescription() + " : " + p.getPrice());
			i++;
		}

	}

	public static void displayMenu(Cart cart) {
		System.out.println("Menu_____");
		System.out.println("1 - Ajouter au panier");
		System.out.println("2 - Supprimer un produit du panier");
		System.out.println("3 - Afficher le panier (" + cart.count() + " articles)");
		System.out.println("4 - Quitter");

	}

	public static void displayCart(Cart cart) {
		if (cart.count() == 0) {
			System.out.println("Votre panier est vide !");
		} else {
			// pour l'affichage, modifier la méthode toString des classes cart et cartline
			// ainsi, on a juste à faire sysout(cart)
//			System.out.println("Panier : ");
//			for (CartLine p : cart.getLines()) {
//				System.out.println(p);
//			}
			System.out.println(cart);
		}

	}

}
