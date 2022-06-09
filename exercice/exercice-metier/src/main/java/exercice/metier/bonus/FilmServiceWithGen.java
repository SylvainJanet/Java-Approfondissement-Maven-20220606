package exercice.metier.bonus;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import exercice.dao.bonus.interfaces.IFilmDaoWithGen;
import exercice.metier.bonus.interfaces.IFilmServiceWithGen;
import exercice.modele.Film;
import exercice.tools.DbConnection;

public class FilmServiceWithGen extends GenericService<Film> implements IFilmServiceWithGen {

	public FilmServiceWithGen(IFilmDaoWithGen dao) {
		super(Film.class, dao);
	}

	@Override
	public boolean watched(long id, int rating) {
		try (Connection cnx = DbConnection.getConnection()) {
			Film filmToEdit = null;
			if ((filmToEdit = dao.findById(id, cnx)) != null) {
				filmToEdit.setWatched(true);
				filmToEdit.setRating(rating);
				int res = dao.update(filmToEdit, cnx);
				return res != 0;
			}
		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		}
		return false;

	}

	// DOUBLE BONUS :

	@Override
	public Film addNewFilm(String title) {
		Film filmAdded = null;
		try (Connection cnx = DbConnection.getConnection()) {
			Film filmToAdd = new Film();
			filmToAdd.setTitle(title);
			filmToAdd.setWatched(false); // inutile, valeur par défaut lors de la création avec le new
			filmToAdd.setRating(null); // idem
			filmAdded = dao.insert(filmToAdd, cnx);
		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		}
		return filmAdded;
	}

	@Override
	public boolean editRating(long id, int rating) {
		return watched(id, rating);
	}

}
