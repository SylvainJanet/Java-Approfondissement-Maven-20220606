package exercice.metier;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import exercice.dao.FilmDao;
import exercice.dao.IFilmDao;
import exercice.modele.Film;
import exercice.tools.DbConnection;

public class FilmService implements IFilmService {

	private IFilmDao dao = new FilmDao();

	@Override
	public List<Film> findAll() {
		List<Film> res = null;
		try (Connection cnx = DbConnection.getConnection()) {
			res = dao.findAll(cnx);
		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public List<Film> findAll(int page, int maxByPage) {
		List<Film> res = null;
		try (Connection cnx = DbConnection.getConnection()) {
			res = dao.findAll(page, maxByPage, cnx);
		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public Film findById(long id) {
		Film res = null;
		try (Connection cnx = DbConnection.getConnection()) {
			res = dao.findById(id, cnx);
		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public Film insert(Film p) {
		Film res = null;
		try (Connection cnx = DbConnection.getConnection()) {
			res = dao.insert(p, cnx);
		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int update(Film p) {
		int res = 0;
		try (Connection cnx = DbConnection.getConnection()) {
			res = dao.update(p, cnx);
		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int delete(long id) {
		int res = 0;
		try (Connection cnx = DbConnection.getConnection()) {
			res = dao.delete(id, cnx);
		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public long count() {
		long res = 0;
		try (Connection cnx = DbConnection.getConnection()) {
			res = dao.count(cnx);
		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public boolean watched(long id, int rating) {
		try (Connection cnx = DbConnection.getConnection()) {
			Film filmToEdit = dao.findById(id, cnx);
			if (filmToEdit != null) {
				filmToEdit.setWatched(true);
				filmToEdit.setRating(rating);
				dao.update(filmToEdit, cnx);
				return true;
			}

		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		}
		return false;
	}

}
