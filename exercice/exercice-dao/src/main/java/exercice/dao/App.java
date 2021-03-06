package exercice.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import exercice.tools.DbConnection;

public class App {

	public static void main(String[] args) {
		// Create the tables in DB
		// Not generically, as it would be too complicated
		// and will be done using annotations in ORM Frameworks such
		// as JPA Hibernate
		// Main issue if you wanted to do it anyway : 
		// What is the primary key ? (property name isn't necessarily id,
		// in practice you annotate it and the ORM uses reflexion to catch
		// the fields having such an annotation)
		// What should be the table and column names ? (ORMs use the same
		// kind of solution)
		// What is type should you use, since they are not the same
		// in a postgres DB and in Java ? What if another DBMS is used ?
		// What if you want to impose some constraints on your table ?
		// What if you want your tables to have relationships ?
		// The complexity skyrockets, but ORMs do provide a solution, for
		// instance the use of annotations to set everything up. Behind
		// the wheel, the ORMs use reflexion to catch that information
		// on relevant entity classes and adapt the request accordingly.
		
		try (Connection cnx = DbConnection.getConnection()) {
			System.out.println("CREATION DE LA TABLE FILM");
			System.out.println("__________");

			String sql = "CREATE TABLE IF NOT EXISTS film(" 
					+ "id SERIAL PRIMARY KEY," 
					+ "title varchar(50) NOT NULL," 
					+ "watched boolean," 
					+ "rating integer"
					+ ")";
			
			cnx.createStatement().execute(sql);
			
			System.out.println("CREATION DE LA TABLE TASK");
			System.out.println("__________");

			sql = "CREATE TABLE IF NOT EXISTS task(" 
					+ "id SERIAL PRIMARY KEY," 
					+ "description varchar(50) NOT NULL," 
					+ "done boolean" 
					+ ")";
			
			cnx.createStatement().execute(sql);
		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		}
		

	}

}
