package exercice.console;

import java.io.IOException;
import java.util.Scanner;

import exercice.console.bonus.FilmGenerique;
import exercice.console.bonus.Taches;
import exercice.metier.FilmService;
import exercice.metier.IFilmService;
import exercice.modele.Film;
import exercice.tools.DisplayConsoleTools;
import exercice.tools.InputTools;

/**
 * Hello world!
 *
 */
public class App {
	private static IFilmService filmService = new FilmService();

	public static void displayFilms() {
		DisplayConsoleTools.displayFilms(filmService.findAll());
	}

	public static void addFilm(Scanner sc) throws IOException {
		System.out.println("Titre ?");
		sc.nextLine();
		String title = sc.nextLine();
		boolean vu = InputTools.readBooleanYN(sc, "Vu ? (Y/N)");
		Integer rating = null;
		if (vu) {
			rating = (int) InputTools.readLong(sc, "Rating ?");
		}
		Film filmToAdd = new Film(0, title, vu, rating);
		filmService.insert(filmToAdd);
		System.out.println("Film ajouté.");
		System.in.read();

	}

	public static void editFilm(Scanner sc) throws IOException {
		long id = InputTools.readLong(sc, "Id du film ?");
		Film filmToEdit = filmService.findById(id);
		if (filmToEdit == null) {
			System.out.println("Film non trouvé !");
		} else {
			System.out.println("Titre ?");
			sc.nextLine();
			String title = sc.nextLine();
			boolean vu = InputTools.readBooleanYN(sc, "Vu ? (Y/N)");
			Integer rating = null;
			if (vu) {
				rating = (int) InputTools.readLong(sc, "Rating ?");
			}
			filmToEdit.setTitle(title);
			filmToEdit.setWatched(vu);
			filmToEdit.setRating(rating);
			filmService.update(filmToEdit);
			System.out.println("Film modifié.");
		}
		System.in.read();
	}

	public static void filmWatched(Scanner sc) throws IOException {
		long id = InputTools.readLong(sc, "Id du film vu ?");
		Film filmToEdit = filmService.findById(id);
		if (filmToEdit == null) {
			System.out.println("Film non trouvé !");
		} else {
			Integer rating = (int) InputTools.readLong(sc, "Rating ?");
			filmService.watched(id, rating);
			System.out.println("Film modifié.");
		}
		System.in.read();
	}

	public static void removeFilm(Scanner sc) throws IOException {
		long id = InputTools.readLong(sc, "Id du film ?");
		int res = filmService.delete(id);
		if (res == 0) {
			System.out.println("Film non trouvé !");
		} else {
			System.out.println("Film supprimé.");
		}
		System.in.read();
	}

	public static void main(String[] args) {
		int choice = 0;
		Scanner sc = new Scanner(System.in);
		try {
			displayFilms();
			do {
				DisplayConsoleTools.displayMenu();
				choice = (int) InputTools.readBoundedLong(sc, "Votre choix ?", 1, 8);
				switch (choice) {
				case 1:
					displayFilms();
					break;
				case 2:
					addFilm(sc);
					break;
				case 3:
					editFilm(sc);
					break;
				case 4:
					removeFilm(sc);
					break;
				case 5:
					filmWatched(sc);
					break;
				case 6:
					FilmGenerique.run(sc);
					System.in.read();
					break;
				case 7:
					Taches.run(sc);
					break;
				}
			} while (choice != 8);
			System.out.println("Au revoir !");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			sc.close();
		}
	}
}
