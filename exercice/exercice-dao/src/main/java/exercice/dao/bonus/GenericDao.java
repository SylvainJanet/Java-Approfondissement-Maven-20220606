package exercice.dao.bonus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import exercice.dao.bonus.interfaces.IGenericDao;
import exercice.tools.GenericTools;

public class GenericDao<T> implements IGenericDao<T> {

	private Class<T> clazz;

	public GenericDao(Class<T> clazz) {
		this.clazz = clazz;
	}

	@Override
	public List<T> findAll(Connection cnx) throws SQLException {
		List<T> list = new ArrayList<T>();

		PreparedStatement ps = cnx
				.prepareStatement("SELECT * from " + GenericTools.getTableName(clazz) + " ORDER BY id");
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			list.add(GenericTools.createObjectFromResultSet(rs, clazz));
		}

		rs.close();

		return list;
	}

	@Override
	public List<T> findAll(int page, int maxByPage, Connection cnx) throws SQLException {
		List<T> lp = new ArrayList<T>();
		PreparedStatement ps = cnx.prepareStatement("SELECT " + GenericTools.getPropertyNamesSeparatedByCommas(clazz)
				+ " FROM " + GenericTools.getTableName(clazz) + " ORDER BY id OFFSET ? LIMIT ?");
		ps.setInt(1, maxByPage * page);
		ps.setInt(2, maxByPage);
		ResultSet res = ps.executeQuery();
		while (res.next()) {
			lp.add(GenericTools.createObjectFromResultSet(res, clazz));
		}
		res.close();

		return lp;
	}

	@Override
	public T findById(long id, Connection cnx) throws SQLException {
		PreparedStatement ps = cnx.prepareStatement("SELECT " + GenericTools.getPropertyNamesSeparatedByCommas(clazz)
				+ " FROM " + GenericTools.getTableName(clazz) + " WHERE id = ?");
		ps.setLong(1, id);
		ResultSet res = ps.executeQuery();
		T obj = null;
		while (res.next()) {
			obj = GenericTools.createObjectFromResultSet(res, clazz);
		}
		res.close();
		return obj;
	}

	@Override
	public T insert(T obj, Connection cnx) throws SQLException {
		int res = 0;
		PreparedStatement ps = cnx.prepareStatement("INSERT INTO " + GenericTools.getTableName(clazz) + " ( "
				+ GenericTools.getPropertyNamesSeparatedByCommasNoId(clazz) + ") VALUES("
				+ GenericTools.getQuestionMarksForPropsWithoutId(clazz) + ")", Statement.RETURN_GENERATED_KEYS);
		try {
			GenericTools.setPropertiesToPreparedStatementsNoId(obj, ps, clazz);
			res = ps.executeUpdate();
			if (res != 0) {
				try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
					if (generatedKeys.next()) {
						GenericTools.setId(obj, generatedKeys.getLong(1), clazz);
					}
				}
			}
		} catch (IllegalArgumentException | IllegalAccessException | SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public int update(T obj, Connection cnx) throws SQLException {
		int res = 0;
		PreparedStatement ps = cnx.prepareStatement("UPDATE " + GenericTools.getTableName(clazz) + " SET "
				+ GenericTools.getPropertyStringForUpdate(clazz) + " WHERE id=?");
		try {
			GenericTools.setPropertiesToPreparedStatementsNoId(obj, ps, clazz);
			GenericTools.setIdToPreparedStatementForUpdate(obj, ps, clazz);
			res = ps.executeUpdate();
		} catch (IllegalArgumentException | IllegalAccessException | SQLException e) {
			e.printStackTrace();
		}

		return res;
	}

	@Override
	public int delete(long id, Connection cnx) throws SQLException {
		int res = 0;
		PreparedStatement ps = cnx.prepareStatement("DELETE FROM " + GenericTools.getTableName(clazz) + " WHERE id=?");
		ps.setLong(1, id);
		res = ps.executeUpdate();

		return res;
	}

	@Override
	public long count(Connection cnx) throws SQLException {
		long nb = 0;
		PreparedStatement ps = cnx.prepareStatement("SELECT COUNT(id) FROM " + GenericTools.getTableName(clazz));
		ResultSet res = ps.executeQuery();
		if (res.next()) {
			nb = res.getLong(1);
		}
		res.close();
		return nb;
	}

}
