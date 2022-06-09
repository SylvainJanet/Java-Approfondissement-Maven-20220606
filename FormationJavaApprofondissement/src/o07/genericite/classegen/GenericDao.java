package o07.genericite.classegen;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import o07.genericite.interfacegen.IGenericDao;

public class GenericDao<T> implements IGenericDao<T> {

	@Override
	public List<T> findAll(Connection cnx) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> findAll(int page, int maxByPage, Connection cnx) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T findById(long id, Connection cnx) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T insert(T p, Connection cnx) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T update(T p, Connection cnx) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(long id, Connection cnx) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long count(Connection cnx) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

}
