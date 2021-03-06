package o05.productDao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import classes.Product;
import o04.jdbc.tools.DbConnection;

public class App5 {

	public static void main(String[] args) {
		/*
		 *
		 * Un DAO (data access object) est un objet qui est responsable de l'accès à une
		 * DB C'est lui qui va créer les requêtes, les envoyer, gérer les ResultSet si
		 * il y en a. Il fait permettre le lien entre notre application et la base de
		 * données
		 * 
		 */

		// Dans le code, on va utiliser ce DAO de la manière suivante :

		try (Connection cnx = DbConnection.getConnection()) {
			System.out.println("TEST DE PRODUCT DAO");
			System.out.println("__________");

			String sql = "CREATE TABLE IF NOT EXISTS product(" + "id SERIAL PRIMARY KEY,"
					+ "description varchar(30) NOT NULL," + "prix double precision NOT NULL" + ")";
			cnx.createStatement().execute(sql);

			IProductDao dao = new ProductDao();

			System.out.println("_____ insert(5,Ordinateur,1000) _____");

			Product ordinateur = dao.insert(new Product(10, "Ordinateur", 1000), cnx);
			List<Product> liste = dao.findAll(cnx);
			for (Product p : liste) {
				System.out.println(p.toString());
			}
			System.out.println("Ordinateur inséré : " + ordinateur);

			System.out.println("_____ insert(2,Clavier,50) _____");

			Product clavier = dao.insert(new Product(2, "Clavier", 50), cnx);
			liste = dao.findAll(cnx);
			for (Product p : liste) {
				System.out.println(p.toString());
			}
			System.out.println("Clavier inséré : " + clavier);

			System.out.println("_____ insert(2,Souris,20) _____");

			Product souris = dao.insert(new Product(2, "Souris", 20), cnx);
			liste = dao.findAll(cnx);
			for (Product p : liste) {
				System.out.println(p.toString());
			}
			System.out.println("Souris inséré : " + souris);

			System.out.println("_____ findAll _____");
			liste = dao.findAll(cnx);
			for (Product p : liste) {
				System.out.println(p);
			}

//			System.out.println("_____ findAll(0,2) _____");
//
//			liste = dao.findAll(0, 2, cnx);
//			for (Product p : liste) {
//				System.out.println(p.toString());
//			}

//			System.out.println("_____ findByDescription(e) _____");
//
//			liste = dao.findByDescription("e", cnx);
//			for (Product p : liste) {
//				System.out.println(p.toString());
//			}

			System.out.println("_____ insert(6,Fraise,5) _____");

			Product fraise = dao.insert(new Product(6, "Fraise", 5), cnx);
			liste = dao.findAll(cnx);
			for (Product p : liste) {
				System.out.println(p.toString());
			}

			System.out.println("_____ update(Fraise price 6) _____");

			System.out.println(fraise);
			System.out.println("id de fraise " + fraise.getId());
			fraise.setPrice(6);
			fraise = dao.update(fraise, cnx);
			System.out.println("Fraise modifiée : " + fraise);
			liste = dao.findAll(cnx);
			for (Product p : liste) {
				System.out.println(p.toString());
			}

			System.out.println("_____ delete(Fraise) _____");

			dao.delete(fraise.getId(), cnx);
			liste = dao.findAll(cnx);
			for (Product p : liste) {
				System.out.println(p.toString());
			}

			System.out.println("_____ count _____");

			System.out.println("Nombre d'éléments : " + dao.count(cnx));

			System.out.println("_____ findById(1) _____");

			Product q = dao.findById(1, cnx);
			System.out.println(q);

		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		}

	}

}
