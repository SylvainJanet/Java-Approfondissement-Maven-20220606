package o05.productDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import classes.Product;

public interface IProductDao {

	List<Product> findAll(Connection cnx) throws SQLException;
	// récupérer toutes les données est une mauvaise pratique,
	// on utilise plutôt des requêtes paginées.

	// Les signatures commentées sont celles qui demandent un peu plus
	// de SQL : à faire plus tard si vous ne connaissez pas SQL,
	// ne pas hésiter à me demander

	List<Product> findAll(int page, int maxByPage, Connection cnx) throws SQLException;

	List<Product> findByDescription(String search, Connection cnx) throws SQLException;
	// la seule méthode de ce DAO qui est spécifique à ce projet, les autres sont
	// les opérations classiques

	Product findById(long id, Connection cnx) throws SQLException; 
	// retourne null si il n'a pas été trouvé

	Product insert(Product p, Connection cnx) throws SQLException; // retourne le produit inséré dans la bae

	Product update(Product p, Connection cnx) throws SQLException; // retourne le produit modifié

	int delete(long id, Connection cnx) throws SQLException; // retourne le nombre de lignes supprimées

	long count(Connection cnx) throws SQLException; // retourne le nombre de lignes dans la table

}
