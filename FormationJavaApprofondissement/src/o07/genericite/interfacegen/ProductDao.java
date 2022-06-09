package o07.genericite.interfacegen;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import classes.Product;

public class ProductDao implements IProductDaoGen {

	@Override
	public List<Product> findAll(Connection cnx) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> findAll(int page, int maxByPage, Connection cnx) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product findById(long id, Connection cnx) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product insert(Product p, Connection cnx) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product update(Product p, Connection cnx) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(long id, Connection cnx) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long count(Connection cnx) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Product> findByDescription(String search, Connection cnx) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
