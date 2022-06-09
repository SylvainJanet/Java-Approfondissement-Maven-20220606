package o04.jdbc.tools;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnection {
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException, IOException {
//		Class.forName("org.postgresql.Driver");
//		String url = "jdbc:postgresql://localhost:5432/FormationJavaApprofondissement20220606"; 
//		String user = "postgres";
//		String pwd = "admin";
//		
//		Connection cnx = DriverManager.getConnection(url,user,pwd);
//		return cnx;
		
		/*
		 * Soucis de sécurité : on écrit le mot de passe dans le code Java
		 * Il est possible que quelqu'un de mal intentionné décompile le code java
		 * et récupère les valeurs codées en dur dans votre appli.
		 * 
		 * On va préférer utiliser un fichier dbconfig.properties qui contiendra les
		 * infos
		 * 
		 * On a notre code, on le compile en JAR, ou en WAR (ou autre), qu'on distribue
		 * aux clients. Ce code compilé ne contient pas les informations sensibles.
		 * On donne à nos client un fichier dbconfig avec des informations de connexion
		 * propre à chaque client
		 * Et du côté SGBD, chaque client a ses identifiants et on restreint leur accès
		 * à la base de données.
		 */
		
		Class.forName("org.postgresql.Driver");
		
		Properties props = new Properties();
		props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("dbconfig.properties"));
		
		String url = props.getProperty("url");
		String user = props.getProperty("userprop");
		String pwd = props.getProperty("pwd");
		
		Connection cnx = DriverManager.getConnection(url,user,pwd);
		return cnx;
	
	}
}
