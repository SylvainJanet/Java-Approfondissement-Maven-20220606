package exercice.tools;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GenericTools {

	public static String getTableName(Class<?> clazz) {
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

	public static String getPropertyNamesSeparated(Class<?> clazz, String separator) {
		String res = "";
		Field[] fields = clazz.getDeclaredFields();
		for (Field f : fields) {
			if (!Modifier.isStatic(f.getModifiers())) {
				res += f.getName() + separator;
			}
		}
		if (res.length() >= separator.length()) {
			res = res.substring(0, res.length() - separator.length());
		}
		return res;
	}

	public static String getPropertyNamesSeparatedNoId(Class<?> clazz, String separator) {
		String res = "";
		Field[] fields = clazz.getDeclaredFields();
		for (Field f : fields) {
			if (!Modifier.isStatic(f.getModifiers()) && !f.getName().toLowerCase().contentEquals("id")) {
				res += f.getName() + separator;
			}
		}
		if (res.length() >= separator.length()) {
			res = res.substring(0, res.length() - separator.length());
		}
		return res;
	}

	public static String getPropertyNamesSeparatedByCommas(Class<?> clazz) {
		return getPropertyNamesSeparated(clazz, ",");
	}

	public static String getPropertyNamesSeparatedByCommasNoId(Class<?> clazz) {
		return getPropertyNamesSeparatedNoId(clazz, ",");

	}

	public static String getQuestionMarksForPropsWithoutId(Class<?> clazz) {
		String res = "";
		Field[] fields = clazz.getDeclaredFields();
		for (Field f : fields) {
			if (!Modifier.isStatic(f.getModifiers()) && !f.getName().toLowerCase().contentEquals("id")) {
				res += "?,";
			}
		}
		if (res.length() >= 3) {
			res = res.substring(0, res.length() - 1);
		}
		return res;
	}

	public static String getPropertyStringForUpdate(Class<?> clazz) {
		String res = "";
		Field[] fields = clazz.getDeclaredFields();
		for (Field f : fields) {
			if (!Modifier.isStatic(f.getModifiers()) && !f.getName().toLowerCase().contentEquals("id")) {
				res += f.getName() + "=?,";
			}
		}
		if (res.length() >= 1) {
			res = res.substring(0, res.length() - 1);
		}
		return res;
	}

	public static <T> T createObjectFromResultSet(ResultSet rs, Class<T> clazz) throws IllegalArgumentException, SQLException {

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

	public static <T> void setPropertiesToPreparedStatementsNoId(T obj, PreparedStatement ps, Class<T> clazz)
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

	public static <T> void setId(T obj, long id, Class<T> clazz) {
		Field[] fields = clazz.getDeclaredFields();
		for (Field f : fields) {
			if (f.getName().toLowerCase().contentEquals("id")) {
				boolean wasAccessible = f.isAccessible();
				if (!wasAccessible)
					f.setAccessible(true);
				try {
					f.set(obj, id);
				} catch (IllegalArgumentException e) {
					System.out.println("Field id should exist on objects of type " + clazz.getName());
				} catch (IllegalAccessException e) {
					System.out.println("Field id was not accessible, but should be to be changed");
				}
				if (!wasAccessible)
					f.setAccessible(false);
			}
		}
	}

	public static <T> void setIdToPreparedStatementForUpdate(T obj, PreparedStatement ps, Class<T> clazz) throws SQLException {
		Field[] fields = clazz.getDeclaredFields();
		int i = 1;
		for (Field f : fields) {
			if (!Modifier.isStatic(f.getModifiers()) && !f.getName().toLowerCase().contentEquals("id")) {
				i++;
			}
		}
		for (Field f : fields) {
			if (f.getName().toLowerCase().contentEquals("id")) {
				boolean wasAccessible = f.isAccessible();
				if (!wasAccessible)
					f.setAccessible(true);
				try {
					ps.setLong(i, f.getLong(obj));
				} catch (IllegalArgumentException e) {
					System.out.println("Field id should exist on objects of type " + clazz.getName());
				} catch (IllegalAccessException e) {
					System.out.println("Field id was not accessible, but should be to be changed");
				}
				if (!wasAccessible)
					f.setAccessible(false);
			}
		}
	}

	public static String getCreateTableRequest(Class<?> clazz) {
		String res = "";

		return res;
	}
}
