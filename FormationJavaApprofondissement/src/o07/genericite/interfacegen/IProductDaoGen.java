package o07.genericite.interfacegen;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import classes.Product;

public interface IProductDaoGen extends IGenericDao<Product> {

	// on définit les signatures des méthodes spécifiques à classe product
	List<Product> findByDescription(String search, Connection cnx) throws SQLException;
}
