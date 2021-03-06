package o04.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import o04.jdbc.tools.DbConnection;

public class O2CreateRow {

	public static void main(String[] args) {

		// on va commencer par créer une classe utilitaire nous permettant d'établir
		// une connexion à notre base de données
		Connection cnx = null;
		try {
			cnx = DbConnection.getConnection();

			/*
			 *
			 * Le DriverManager assure la liaison avec le pilote et nous permet d'établir la
			 * connexion.
			 * 
			 * On utilise cette connexion pour transmettre des instructions vers la base.
			 * Ces requêtes sont exécutées grâce à une interface Statement. Pour les
			 * requêtes paramétrées : PreparedStatement Pour les procédures stockées :
			 * CallableStatement
			 * 
			 * Les éventuels résultats sont accessible avec un élément ResultSet
			 * 
			 */

//			String nom = "DUSAUSSOY";
//			String prenom = "Mikael";
//			String age = "32";
//			String sql = "INSERT INTO personne (nom,prenom,age) VALUES ('"+nom+"','"+prenom+"',"+age+")";

			// cnx.createStatement().execute(sql);

			// Ne jamais faire les choses comme ça, on peut avoir un problème d'injection
			// SQL
			// Un utilisateur mal intentionné rentre les informations suivantes :
			// age = 45
			// nom = Truc
			// prenom = bidule',20) DELETE FROM personne INSERT INTO personne (nom, prenom,
			// age) VALUES ('Hahahaha', 'X

			// ma chaine de caractères sql sera :
//			sql = "INSERT INTO personne (nom,prenom,age) VALUES ('Truc','bidule',20) "
//					+ "DELETE FROM personne " // supprime toutes les lignes de la table
//					+ "INSERT INTO personne (nom, prenom, age) VALUES ('Hahahaha', 'X',45)";
			// cas plus pratique d'utilisation d'injection :
			// https://www.youtube.com/watch?v=ciNHn38EyRc

			// on fera plutôt ça :
//			String nom = "THIERY";
//			String prenom = "Benjamin";
//			int age = 29;
//			
//			String sql = "INSERT INTO personne (nom,prenom,age) VALUES (?,?,?)";
//			
//			PreparedStatement ps = cnx.prepareStatement(sql);
//			ps.setString(1, nom);
//			ps.setString(2, prenom);
//			ps.setInt(3, age);

			// deux méthodes utiles : executeQuery et executeUpdate
			// la première retourne des résultat et la seconde n'attend aucune valeur de la
			// DB
			// executeQuery renvoie le resultSet
			// executeUpdate renvoie le nombre de lignes modifiées

			// execute est possible : renvoie un booléen qui indique si un resultSet est
			// envoyé
			// pour l'utilisation : getResultSet() pour obtenir le resultSet si il y en a,
			// et sinon
			// getUpdateCount() pour obtenir le nombre de lignes modifiées

//			ps.executeUpdate();

			// une transaction est un ensemble d'instructions SQL qui ne sont effectivements
			// appliqués
			// à la base de données uniquemenent si il n'y a aucune erreur.

			// virement bancaire : 2 étapes
			// 1) retirer le montant du compte débiteur
			// 2) ajouter le montant au compte bénéficiaire
			// si entre l'étape 1 et l'étape 2 il y a un problème ?
			// si on utilise pas de transaction : seule l'étape 1) est effectuée : problème
			// en utilisant une transaction : en cas d'erreur, rien n'est fait
			// c'est lorsque toutes les instructions se sont produites sans erreurs que les
			// modifications
			// sont effectivement faites en DB

			/*
			 * 
			 * Vocabulaire des transactions : Commit : faire un commit sur une transaction
			 * signifie valider tous les changements et les effectuer réellement dans la
			 * base Rollback : faire un rollback sur une transaction signifie annuler tous
			 * les changements de la transaction
			 * 
			 */

			/*
			 * 
			 * Par défaut, la connexion est en mode auto-commit : chaque instruction envoyée
			 * fait partie d'une transaction qui est commit directement automatiquement
			 * 
			 */
			cnx.setAutoCommit(false);

			String nom = "DECLE";
			String prenom = "Alexis";
			int age = 18;

			String sql = "INSERT INTO personne (nom,prenom,age) VALUES (?,?,?)";

			PreparedStatement ps = cnx.prepareStatement(sql);
			ps.setString(1, nom);
			ps.setString(2, prenom);
			ps.setInt(3, age);

			ps.executeUpdate();

//			ps.setString(1, "DUSAUSSOY");
//			ps.setString(2, "Mikael");
//			ps.setInt(3, 32);
			ps.setString(1, "FRANCELLE");
			ps.setString(2, "Anthony");
			ps.setInt(3, 18);

			ps.executeUpdate();

			ps.close();
			
			// lorsqu'on ferme la connexion, toutes les ressources ouvertes par la connexion
			// sont également fermées
			// par contre, si on veut la garder la connexion mais faire plusieurs preparedStatement
			// il faut les fermer au fur et à mesure

			cnx.commit();
		} catch (ClassNotFoundException | SQLException | IOException e) {
			try {
				cnx.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				cnx.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
}
