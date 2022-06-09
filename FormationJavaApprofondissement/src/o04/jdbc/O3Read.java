package o04.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import classes.Personne;
import o04.jdbc.tools.DbConnection;

public class O3Read {

	public static void main(String[] args) {

		Connection cnx = null;
		try {
			cnx = DbConnection.getConnection();

			List<Personne> liste = new ArrayList<>();

			String sql = "SELECT * FROM personne";
			// à ne pas faire dans une vraie appli
			// parce que si la table explose en taille, la requête va prendre énormément de
			// temps
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
			ps.close();
			cnx.close();

			for (Personne personne : liste) {
				System.out.println(personne);
			}

		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		}

	}
}
