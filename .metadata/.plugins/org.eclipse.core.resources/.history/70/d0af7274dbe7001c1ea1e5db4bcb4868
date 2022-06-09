package exercice.dao.bonus.interfaces;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface IGenericDao<T> {
	
	List<T> findAll(Connection cnx) throws SQLException;

	List<T> findAll(int page, int maxByPage, Connection cnx) throws SQLException;

	T findById(long id, Connection cnx) throws SQLException;

	T insert(T p, Connection cnx) throws SQLException;

	int update(T p, Connection cnx) throws SQLException;

	int delete(long id, Connection cnx) throws SQLException;

	long count(Connection cnx) throws SQLException;
}
