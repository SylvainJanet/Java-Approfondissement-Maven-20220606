package exercice.metier.bonus.interfaces;

import exercice.modele.Film;

public interface IFilmServiceWithGen extends IGenericService<Film> {

	boolean watched(long id, int rating);
	
	// DOUBLE BONUS :

	Film addNewFilm(String title);

	boolean editRating(long id, int rating);
}
