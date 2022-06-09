package exercice.tools;

import java.util.List;

import exercice.modele.Film;

public class DisplayConsoleTools {

	public static void displayFilms(List<Film> lf) {
		System.out.println("Liste des films ___________");
		int i = 1;
		for (Film f : lf) {
			System.out.println(i + "- " + f.toString());
			i++;
		}
	}

	public static <T> void displayEntity(Class<T> clazz, List<T> l) {
		System.out.println("Liste des " + clazz.getSimpleName() + "s ___________");
		int i = 1;
		for (T f : l) {
			System.out.println(i + "- " + f.toString());
			i++;
		}
	}

	public static void displayMenu() {
		System.out.println("Menu _________________________");
		System.out.println("1 - Afficher films");
		System.out.println("2 - Ajouter film");
		System.out.println("3 - Modifier film");
		System.out.println("4 - Supprimer film");
		System.out.println("5 - J'ai vu un film");
		System.out.println("6 - BONUS : Films en utilisant les génériques");
		System.out.println("7 - BONUS : Gestion des tâches");
		System.out.println("8 - Quitter");
	}

	public static void displayMenuFilmGeneric() {
		System.out.println("Menu _________________________");
		System.out.println("1 - Afficher films");
		System.out.println("2 - Ajouter film");
		System.out.println("3 - Modifier film");
		System.out.println("4 - Supprimer film");
		System.out.println("5 - J'ai vu un film");
		System.out.println("6 - Quitter");
	}

	public static void displayMenuTasks() {
		System.out.println("Menu _________________________");
		System.out.println("1 - Afficher tâches");
		System.out.println("2 - Ajouter tâche");
		System.out.println("3 - Modifier tâche");
		System.out.println("4 - Supprimer tâche");
		System.out.println("5 - J'ai effectué une tâche");
		System.out.println("6 - Quitter");
	}
}
