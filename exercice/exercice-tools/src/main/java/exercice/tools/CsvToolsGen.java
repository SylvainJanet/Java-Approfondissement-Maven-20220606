package exercice.tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class CsvToolsGen {

	public static <T> void toCsv(String filePath, List<T> lp, String separator)
			throws IOException, IllegalArgumentException, IllegalAccessException {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
			if (lp != null && lp.size() > 0) {

				Field[] fields = lp.get(0).getClass().getDeclaredFields();
				int i = 0;
				for (Field f : fields) {
					boolean wasAccessible = f.isAccessible();
					if (!wasAccessible)
						f.setAccessible(true);

					if (!Modifier.isStatic(f.getModifiers()))
						bw.write(f.getName());

					if (!wasAccessible)
						f.setAccessible(false);

					if (i < fields.length - 1 && !Modifier.isStatic(f.getModifiers()))
						bw.write(separator);
					i++;
				}
				bw.newLine();

				i = 1;
				for (T t : lp) {

					int j = 0;
					for (Field f : fields) {
						boolean wasAccessible = f.isAccessible();
						if (!wasAccessible)
							f.setAccessible(true);

						if (!Modifier.isStatic(f.getModifiers())) {
							if (f.get(t) != null)
								bw.write(f.get(t).toString());
							else
								bw.write("null");
						}

						if (!wasAccessible)
							f.setAccessible(false);

						if (j < fields.length - 1 && !Modifier.isStatic(f.getModifiers()))
							bw.write(separator);
						j++;
					}

					if (i < lp.size())
						bw.newLine();
					i++;
				}
			}
		}
	}

	public static <T> List<T> fromCsv(Class<T> clazz, String filePath, String separator) throws IOException,
			NumberFormatException, IllegalArgumentException, IllegalAccessException, InstantiationException {
		List<T> res = new ArrayList<T>();
		Field[] fields = clazz.getDeclaredFields();
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line = br.readLine();
			while ((line = br.readLine()) != null) {
				if (!line.trim().isEmpty()) {
					String[] champs = line.trim().split(separator);
					T t = clazz.newInstance();
					int j = 0;
					for (int i = 0; i < fields.length; i++) {
						if (!Modifier.isStatic(fields[i].getModifiers())) {
							boolean wasAccessible = fields[i].isAccessible();
							if (!wasAccessible)
								fields[i].setAccessible(true);

							switch (fields[i].getType().getName()) {
							case "byte":
								fields[i].set(t, Byte.parseByte(champs[j]));
								break;
							case "short":
								fields[i].set(t, Short.parseShort(champs[j]));
								break;
							case "int":
								fields[i].set(t, Integer.parseInt(champs[j]));
								break;
							case "long":
								fields[i].set(t, Long.parseLong(champs[j]));
								break;
							case "char":
								fields[i].set(t, champs[j].toCharArray()[0]);
								break;
							case "float":
								fields[i].set(t, Float.parseFloat(champs[j]));
								break;
							case "double":
								fields[i].set(t, Double.parseDouble(champs[j]));
								break;
							case "boolean":
								fields[i].set(t, Boolean.parseBoolean(champs[j]));
								break;
							default:
								fields[i].set(t, fields[i].getType().cast(champs[j]));
								break;

							}
							if (!wasAccessible)
								fields[i].setAccessible(false);

							j++;
						}
					}
					res.add(t);
				}
			}
		}
		return res;
	}
}
