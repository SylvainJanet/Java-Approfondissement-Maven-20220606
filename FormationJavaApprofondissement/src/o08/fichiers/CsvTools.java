package o08.fichiers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import classes.Product;

public class CsvTools {

	public static void toCsv(String filePath, List<Product> list, String separator) throws IOException {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {

			bw.write("id" + separator + "description" + separator + "prix");
			bw.newLine();
			for (Product product : list) {
				String line = product.getId() + separator + product.getDescription() + separator + product.getPrice();
				bw.write(line);
				bw.newLine();
			}
		}

	}

	public static List<Product> fromCsv(String filePath, String separator) throws FileNotFoundException, IOException {
		List<Product> res = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line = br.readLine();
			while ((line = br.readLine()) != null) { // on affecte une valeur à line et on teste
				// si cette nouvelle valeur est != null
				// par exemple, line = "2,Clavier,50"
				String[] champs = line.split(separator);

				long id = Long.parseLong(champs[0]);
				String description = champs[1];
				double prix = Double.parseDouble(champs[2]);

				res.add(new Product(id, description, prix));
			}
		}
		return res;
	}

}
