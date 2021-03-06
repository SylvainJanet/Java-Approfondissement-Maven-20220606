package o09.reflexion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class CsvToolsGen {

	public static <T> void toCsv(String filePath, List<T> list, String separator, Class<T> clazz)
			throws IOException, IllegalArgumentException, IllegalAccessException {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {

			// première ligne : les noms des champs séparés par le separator
			String firstLine = "";
			// dans la pratique : utiliser StringBuilder (une classe peut avoir énormément
			// de champs)

			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				firstLine += field.getName();
				firstLine += separator;
			}
			if (firstLine.length() != 0) {
//				"Ma chaine de Chars" -> "haine de Ch"
//				"hamburger".substring(4, 8) returns "urge"
				firstLine = firstLine.substring(0, firstLine.length() - separator.length());
			}
			bw.write(firstLine);
			bw.newLine();
			for (T obj : list) {
				StringJoiner line = new StringJoiner(separator);
				for (Field field : fields) {
					field.setAccessible(true);
					line.add(field.get(obj).toString()); // field.get(obj) -> obj.fieldName
				}

				bw.write(line.toString());
				bw.newLine();
			}
		}

	}

	public static <T> List<T> fromCsv(String filePath, String separator, Class<T> clazz)
			throws InstantiationException, IllegalAccessException, IOException {
		List<T> res = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line = br.readLine();
			while ((line = br.readLine()) != null) {
				String[] champs = line.split(separator);

				T obj = clazz.newInstance();

				// mettre les bonnes valeurs aux attributs de obj avant de l'ajouter à la liste
				// ils sont dans le tableau champs sous forme de chaine de caractères

				Field[] fields = clazz.getDeclaredFields();
				for (int i = 0; i < fields.length; i++) {
					fields[i].setAccessible(true);

//					fields[i].set(obj, fields[i].getType().cast(champs[i]));
					// field.set(obj,value) -> obj.fieldName = value
					// les objets de type classe ne sont pas adapté pour les types primitifs,
					// c'est à dire byte, short, int, long, char, float, double, boolean
					String typeName = fields[i].getType().getName();

					switch (typeName) {
					case "byte":
						fields[i].set(obj, Byte.valueOf(champs[i]));
						break;
					case "short":
						fields[i].set(obj, Short.valueOf(champs[i]));
						break;
					case "int":
						fields[i].set(obj, Integer.valueOf(champs[i]));
						break;
					case "long":
						fields[i].set(obj, Long.valueOf(champs[i]));
						break;
					case "char":
						fields[i].set(obj, champs[i].toCharArray()[0]);
						break;
					case "float":
						fields[i].set(obj, Float.valueOf(champs[i]));
						break;
					case "double":
						fields[i].set(obj, Double.valueOf(champs[i]));
						break;
					case "boolean":
						fields[i].set(obj, Boolean.valueOf(champs[i]));
						break;
					default:
						fields[i].set(obj, fields[i].getType().cast(champs[i]));
						break;
					}
				}
				res.add(obj);
			}
		}
		return res;
	}

}
