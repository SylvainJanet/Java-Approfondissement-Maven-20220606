package exercice.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import exercice.modele.Film;

public interface IFilmDao {

	List<Film> findAll(Connection cnx) throws SQLException;

	List<Film> findAll(int page, int maxByPage, Connection cnx) throws SQLException;

	Film findById(long id, Connection cnx) throws SQLException;

	Film insert(Film p, Connection cnx) throws SQLException;

	int update(Film p, Connection cnx) throws SQLException;

	int delete(long id, Connection cnx) throws SQLException;

	long count(Connection cnx) throws SQLException;

}
