package o04.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import o04.jdbc.tools.DbConnection;
import o04.jdbc.tools.DisplayDbValues;

public class O5Delete {

	public static void main(String[] args) {
		try (Connection cnx = DbConnection.getConnection()) {

			System.out.println("_____ DB before _____");
			DisplayDbValues.printPersonne(cnx);

			String sql = "DELETE FROM personne WHERE id=?";
			
			PreparedStatement ps = cnx.prepareStatement(sql);
			
			ps.setLong(1, 18);
			ps.executeUpdate();

			System.out.println("_____ DB after _____");
			DisplayDbValues.printPersonne(cnx);
			
			// on créé une table en base de données, puis on a créé une classe correspondante
			// dans le travail pratique, on a créé la classe Product puis vous allez devoir créer 
			// la table correspondante
			// si l'un est modifié, l'autre doit être modifié également de la même manière pour
			// pouvoir continuer à communiquer
			// il y a donc une forte dépendance entre deux éléments qui sont découplés
			
			// En pratique, on utilise souvent un ORM (object relational mapping) comme Hibernate
			// qui permet de faire le pont entre le modèle objet et la base de données
			
			// par exemple : en partant d'une base de données, générer le code des classes
			// ou inversement : en partant du code, générer la base données
			
			// avantages : 
			// - lier le code à la base de données, de centraliser le modèle objet,
			// si l'un est modifié, l'autre l'est aussi
			// - d'autres langages de requêtes sont utilisés, qui permettent de profiter pleinement
			// de la structure des objets
			// On peut faire des requête du type : 
			// SELECT * FROM salarie WHERE salarie.adresse.codepostal == 90000
			// En SQL, on aurait eu besoin de faire une jointure
			// Parfois, on utilise même plus un langage de requête, mais on a une bibliothèque du type :
			// request.table(Personne).select().where(p.adresse.codepostal == 90000)
			
			// inconvénients : 
			// - le SQL pur est meilleur en terme de performance. Les ORM, en toute finalité,
			// communiquent en SQL : coût en génération de code et en optimalité du code SQL généré
			// - certaines fonctionnalités SQL ne sont parfois pas implémentées dans les langages
			// de requêtes spécifiques aux ORM
		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		}

	}

}
