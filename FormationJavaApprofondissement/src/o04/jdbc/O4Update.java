package o04.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import o04.jdbc.tools.DbConnection;
import o04.jdbc.tools.DisplayDbValues;

public class O4Update {

	public static void main(String[] args) {
		
		// pour les ressources qui nécessitent d'être fermées
		// (pour les objets qui implémentent l'interface Closeable ou équivalent)
		// on peut utiliser le ContextManager pour fermer la ressource pour nous :
		
		try (Connection cnx = DbConnection.getConnection()) {
			
			System.out.println("_____ DB before _____");
			DisplayDbValues.printPersonne(cnx);
			
			String sql = "UPDATE personne SET nom=?, prenom=?, age=? WHERE id=?";
			
			PreparedStatement ps = cnx.prepareStatement(sql);
			
			ps.setString(1, "DECLE");
			ps.setString(2, "Alexis");
			ps.setInt(3, 50);
			ps.setLong(4, 17);
			
			int nbrLignesModifiees = ps.executeUpdate();
			
			System.out.println("_____ DB after (" + nbrLignesModifiees + " lines updated) _____");
			DisplayDbValues.printPersonne(cnx);
			
		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		} 
	}
}
