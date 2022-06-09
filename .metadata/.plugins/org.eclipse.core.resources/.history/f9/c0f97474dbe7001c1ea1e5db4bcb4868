package exercice.dao.bonus;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import exercice.dao.bonus.interfaces.IGenericDao;

public class GenericDao<T> implements IGenericDao<T> {

	private Class<T> clazz;

	public GenericDao(Class<T> clazz) {
		this.clazz = clazz;
	}

	private String getTableName() {
		return clazz.getSimpleName().toLowerCase();
		// assumes tables are named after the classes.
		// if it is not the case, one could use a
		// Map<Class<?>, String> constant to represent the mapping
		// between the classes and the tables names in DB and use
		// it here. The same could be said for column names.

		// here for simplicity, we will assume that table and columns
		// names in db are the same (respectively) as the classes
		// names and property names
	}

	private String getPropertyNamesSeparatedByCommas() {
		String res = "";
		Field[] fields = clazz.getDeclaredFields();
		for (Field f : fields) {
			if (!Modifier.isStatic(f.getModifiers())) {
				res += f.getName() + " , ";
			}
		}
		if (res.length() >= 2) {
			res = res.substring(0, res.length() - 2);
		}
		return res;
	}

	private String getPropertyNamesSeparatedByCommasNoId() {
		String res = "";
		Field[] fields = clazz.getDeclaredFields();
		for (Field f : fields) {
			if (!Modifier.isStatic(f.getModifiers()) && !f.getName().toLowerCase().contentEquals("id")) {
				res += f.getName() + " , ";
			}
		}
		if (res.length() >= 2) {
			res = res.substring(0, res.length() - 2);
		}
		return res;
	}

	private String getQuestionMarksForPropsWithoutId() {
		String res = "";
		Field[] fields = clazz.getDeclaredFields();
		for (Field f : fields) {
			if (!Modifier.isStatic(f.getModifiers()) && !f.getName().toLowerCase().contentEquals("id")) {
				res += "? , ";
			}
		}
		if (res.length() >= 2) {
			res = res.substring(0, res.length() - 2);
		}
		return res;
	}

	private String getPropertyStringForUpdate() {
		String res = "";
		Field[] fields = clazz.getDeclaredFields();
		for (Field f : fields) {
			if (!Modifier.isStatic(f.getModifiers()) && !f.getName().toLowerCase().contentEquals("id")) {
				res += f.getName() + "=? , ";
			}
		}
		if (res.length() >= 2) {
			res = res.substring(0, res.length() - 2);
		}
		return res;
	}

	private T createObjectFromResultSet(ResultSet rs) throws IllegalArgumentException, SQLException {

		T res = null;
		try {
			res = clazz.newInstance();
			Field[] fields = clazz.getDeclaredFields();
			for (Field f : fields) {
				if (!Modifier.isStatic(f.getModifiers())) {
					boolean wasAccessible = f.isAccessible();
					if (!wasAccessible)
						f.setAccessible(true);

					switch (f.getType().getName()) {
					case "byte":
						f.set(res, rs.getByte(f.getName()));
						break;
					case "short":
						f.set(res, rs.getShort(f.getName()));
						break;
					case "int":
						f.set(res, rs.getInt(f.getName()));
						break;
					case "long":
						f.set(res, rs.getLong(f.getName()));
						break;
					case "char":
						f.set(res, rs.getString(f.getName()).charAt(0));
						break;
					case "float":
						f.set(res, rs.getFloat(f.getName()));
						break;
					case "double":
						f.set(res, rs.getDouble(f.getName()));
						break;
					case "boolean":
						f.set(res, rs.getBoolean(f.getName()));
						break;
					default:
						f.set(res, f.getType().cast(rs.getObject(f.getName())));
						break;

					}
					if (!wasAccessible)
						f.setAccessible(false);

				}
			}
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return res;
	}

	private void setPropertiesToPreparedStatementsNoId(T obj, PreparedStatement ps)
			throws IllegalArgumentException, IllegalAccessException, SQLException {
		Field[] fields = clazz.getDeclaredFields();
		int i = 1;
		for (Field f : fields) {
			if (!Modifier.isStatic(f.getModifiers()) && !f.getName().toLowerCase().contentEquals("id")) {
				boolean wasAccessible = f.isAccessible();
				if (!wasAccessible)
					f.setAccessible(true);

				switch (f.getType().getName()) {
				case "byte":
					ps.setByte(i, f.getByte(obj));
					break;
				case "short":
					ps.setShort(i, f.getShort(obj));
					break;
				case "int":
					ps.setInt(i, f.getInt(obj));
					break;
				case "long":
					ps.setLong(i, f.getLong(obj));
					break;
				case "char":
					ps.setString(i, Character.toString(f.getChar(obj)));
					break;
				case "float":
					ps.setFloat(i, f.getFloat(obj));
					break;
				case "double":
					ps.setDouble(i, f.getDouble(obj));
					break;
				case "boolean":
					ps.setBoolean(i, f.getBoolean(obj));
					break;
				default:
					ps.setObject(i, f.get(obj));
					break;

				}
				if (!wasAccessible)
					f.setAccessible(false);
				i++;
			}
		}
	}

	private void setId(T obj, long id) {
		Field[] fields = clazz.getDeclaredFields();
		for (Field f : fields) {
			if (f.getName().toLowerCase().contentEquals("id")) {
				f.setAccessible(true);
				try {
					f.set(obj, id);
				} catch (IllegalArgumentException e) {
					System.out.println("Field id should exist on objects of type " + clazz.getName());
				} catch (IllegalAccessException e) {
					System.out.println("Field id was not accessible, but should be to be changed");
				}
				f.setAccessible(false);
			}
		}
	}

	private void setIdToPreparedStatementForUpdate(T obj, PreparedStatement ps) throws SQLException {
		Field[] fields = clazz.getDeclaredFields();
		int i = 1;
		for (Field f : fields) {
			if (!Modifier.isStatic(f.getModifiers()) && !f.getName().toLowerCase().contentEquals("id")) {
				i++;
			}
		}
		for (Field f : fields) {
			if (f.getName().toLowerCase().contentEquals("id")) {
				f.setAccessible(true);
				try {
					ps.setLong(i, f.getLong(obj));
				} catch (IllegalArgumentException e) {
					System.out.println("Field id should exist on objects of type " + clazz.getName());
				} catch (IllegalAccessException e) {
					System.out.println("Field id was not accessible, but should be to be changed");
				}
				f.setAccessible(false);
			}
		}
	}

	@Override
	public List<T> findAll(Connection cnx) throws SQLException {
		List<T> list = new ArrayList<T>();

		PreparedStatement ps = cnx.prepareStatement("SELECT * from " + getTableName() + " ORDER BY id");
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			list.add(createObjectFromResultSet(rs));
		}

		rs.close();

		return list;
	}

	@Override
	public List<T> findAll(int page, int maxByPage, Connection cnx) throws SQLException {
		List<T> lp = new ArrayList<T>();
		PreparedStatement ps = cnx.prepareStatement("SELECT " + getPropertyNamesSeparatedByCommas() + " FROM "
				+ getTableName() + " ORDER BY id OFFSET ? LIMIT ?");
		ps.setInt(1, maxByPage * page);
		ps.setInt(2, maxByPage);
		ResultSet res = ps.executeQuery();
		while (res.next()) {
			lp.add(createObjectFromResultSet(res));
		}
		res.close();

		return lp;
	}

	@Override
	public T findById(long id, Connection cnx) throws SQLException {
		PreparedStatement ps = cnx.prepareStatement(
				"SELECT " + getPropertyNamesSeparatedByCommas() + " FROM " + getTableName() + " WHERE id = ?");
		ps.setLong(1, id);
		ResultSet res = ps.executeQuery();
		T obj = null;
		while (res.next()) {
			obj = createObjectFromResultSet(res);
		}
		res.close();
		return obj;
	}

	@Override
	public T insert(T obj, Connection cnx) throws SQLException {
		int res = 0;
		PreparedStatement ps = cnx.prepareStatement("INSERT INTO " + getTableName() + " ( "
				+ getPropertyNamesSeparatedByCommasNoId() + ") VALUES(" + getQuestionMarksForPropsWithoutId() + ")",
				Statement.RETURN_GENERATED_KEYS);
		try {
			setPropertiesToPreparedStatementsNoId(obj, ps);
			res = ps.executeUpdate();
			if (res != 0) {
				try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
					if (generatedKeys.next()) {
						setId(obj, generatedKeys.getLong(1));
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
		PreparedStatement ps = cnx
				.prepareStatement("UPDATE " + getTableName() + " SET " + getPropertyStringForUpdate() + " WHERE id=?");
		try {
			setPropertiesToPreparedStatementsNoId(obj, ps);
			setIdToPreparedStatementForUpdate(obj, ps);
			res = ps.executeUpdate();
		} catch (IllegalArgumentException | IllegalAccessException | SQLException e) {
			e.printStackTrace();
		}

		return res;
	}

	@Override
	public int delete(long id, Connection cnx) throws SQLException {
		int res = 0;
		PreparedStatement ps = cnx.prepareStatement("DELETE FROM " + getTableName() + " WHERE id=?");
		ps.setLong(1, id);
		res = ps.executeUpdate();

		return res;
	}

	@Override
	public long count(Connection cnx) throws SQLException {
		long nb = 0;
		PreparedStatement ps = cnx.prepareStatement("SELECT COUNT(id) FROM " + getTableName());
		ResultSet res = ps.executeQuery();
		if (res.next()) {
			nb = res.getLong(1);
		}
		res.close();
		return nb;
	}

}
