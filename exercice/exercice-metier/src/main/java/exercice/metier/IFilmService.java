package exercice.metier;

import java.util.List;

import exercice.modele.Film;

public interface IFilmService {
	List<Film> findAll();

	List<Film> findAll(int page, int maxByPage);

	Film findById(long id);

	Film insert(Film p);

	int update(Film p);

	int delete(long id);

	long count();

	boolean watched(long id, int rating);
}
