package exercice.metier.bonus;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import exercice.dao.bonus.interfaces.IGenericDao;
import exercice.metier.bonus.interfaces.IGenericService;
import exercice.tools.DbConnection;

public class GenericService<T> implements IGenericService<T> {

	protected IGenericDao<T> dao;

	public GenericService(Class<T> clazz, IGenericDao<T> dao) {
		this.dao = dao;
	}

	@Override
	public List<T> findAll() {
		List<T> res = null;
		try (Connection cnx = DbConnection.getConnection()) {
			res = dao.findAll(cnx);
		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public List<T> findAll(int page, int maxByPage) {
		List<T> res = null;
		try (Connection cnx = DbConnection.getConnection()) {
			res = dao.findAll(page, maxByPage, cnx);
		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public T findById(long id) {
		T res = null;
		try (Connection cnx = DbConnection.getConnection()) {
			res = dao.findById(id, cnx);
		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public T insert(T p) {
		T res = null;
		try (Connection cnx = DbConnection.getConnection()) {
			res = dao.insert(p, cnx);
		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int update(T p) {
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

}
