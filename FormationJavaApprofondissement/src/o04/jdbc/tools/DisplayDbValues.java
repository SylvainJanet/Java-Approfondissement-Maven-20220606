package o04.jdbc.tools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import classes.Personne;

public class DisplayDbValues {

	public static void printPersonne(Connection cnx) throws SQLException {
		
		List<Personne> liste = new ArrayList<>();

		String sql = "SELECT * FROM personne";
		PreparedStatement ps = cnx.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();
		// on peut un ResultSet comme un curseur qui peut parcourir (ou sens
		// d'itérateur) l'ensemble
		// des résultat. Au départ, le curseur est avant la première ligne des résultats
		while (rs.next()) {
			Personne p = new Personne();

			long id = rs.getLong("id");
			String nom = rs.getString("nom");
			String prenom = rs.getString(3); // on peut donner le nom de la colonne en base
			// de données ou la position de la colonne (position commence par 1)
			int age = rs.getInt("age");

			p.setId(id);
			p.setNom(nom);
			p.setPrenom(prenom);
			p.setAge(age);

			liste.add(p);
		}

		for (Personne personne : liste) {
			System.out.println(personne);
		}
	}
}
