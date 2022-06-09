package o04.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class O1CreateTable {

	/*
	 * 
	 * Une base de données relationnelle est une base de données (ensemble de
	 * tables) + des éventuelles relations entre les tables relations : - one-to-one
	 * : un objet de type A est associé à un et un seul objet de type B et
	 * inversement. Par exemple : un crâne est associé à un seul cerveau. -
	 * one-to-many / many-to-one : une entreprise a plusieurs employés, mais un
	 * employé est associé à une seule entreprise - many-to-many : un panier de
	 * course est lié à plusieurs produits, et un produit peut faire parti de
	 * plusieurs paniers de course
	 * 
	 * pour effectuer ses relations, mais aussi pour identifier chaque élément de
	 * manière unique on utilise un système de clé primaire (id), ou étrangère.
	 * 
	 * Pour communiquer avec la base de données : on utilise le SQL (structured
	 * query langage) Pourquoi structuré ? Toutes les lignes d'une table on
	 * forcément les même colonnes (potentiellement null) Quand on fait du big data
	 * (analyse de données en très grande quantité), on peut utiliser des bases non
	 * structurées et on a besoin d'utiliser dautres langages pour communiquer
	 * 
	 * Il existe plusieurs SGBD (système de gestion de base de données) mySql,
	 * SqlServer, mariaDb, postGreSql,...
	 * 
	 * JDBC : Java DataBase Connectivity - une API qui permet un accès aux base
	 * données, quel que soit le SGBD utilisé
	 * 
	 * Installation de PostGreSQL (utilisé dans cette formation)
	 * 
	 * Ces classes sont regroupées dans un package, java.sql et incluses dans le jdk
	 * Essentiellement, ce sont des interfaces
	 * 
	 * Pour pouvoir utiliser JDBC, il faut un pilote qui est spécifique à la base de
	 * données à laquelle on veut accéder. Le pilote va être constitué de classes
	 * implémentant ces interfaces. Elles sont en général développées par le
	 * concepteur de la base de données
	 * 
	 * Installation du driver
	 * 
	 * On va créer une base de données depuis le SGBD mais tout faire par la suite
	 * en utilisant JDBC - on ne fera pas de gestion de base de données autre que la
	 * création
	 * 
	 * Ouvrir pgAdmin4
	 * 
	 * A voir : Méthodologie pour mettre en place : technique/méthode Merise
	 * 
	 */
	public static void main(String[] args) {
		try {
			Class.forName("org.postgresql.Driver"); // package du driver, permet de charger 
			// le driver en mémoire et de l'utiliser avec JDBC
			// pour obtenir la chaîne de caractères à utiliser pour les autres SGBD, la 
			// documentation du driver le fournit
			
			// C'est la seule différence dans l'utilisation de JDBC entre l'utilisation de
			// postgreSQL ou un autre SGBD
			
			// Class.forName se charge d'aller chercher en mémoire la classe. Le DriverManager
			// qui s'occupe automatiquement de charger le pilote et de créer une instance de 
			// la classe
			
			// connection à la base de données
			String url = "jdbc:postgresql://localhost:5432/FormationJavaApprofondissement20220606"; // remplacer test par 
			// le nom de la base de données
			String user = "postgres";
			String pwd = "admin"; // votre mdp
			
			Connection cnx = DriverManager.getConnection(url,user,pwd);
			
			String sql = "CREATE TABLE IF NOT EXISTS personne("
					+ "id SERIAL PRIMARY KEY," // clé primaire, SERIAL ~ AUTOINCREMENT
					// c'est la base de données qui se charge de créer les ids lors de l'ajout
					// de données
					+ "nom varchar(30) NOT NULL,"
					+ "prenom varchar(20) NOT NULL,"
					+ "age int CHECK (age > 0),"
					+ "CONSTRAINT unique_names UNIQUE (nom,prenom)" // pas d'homonymes dans ma
					// table
					+ ")"; 
			cnx.createStatement().execute(sql);
			
			cnx.close();
			
			System.out.println("La table a été créée");
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

}
