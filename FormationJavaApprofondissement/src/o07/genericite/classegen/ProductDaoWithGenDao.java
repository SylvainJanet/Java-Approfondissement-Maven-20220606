package o07.genericite.classegen;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import classes.Product;
import o05.productDao.IProductDao;

public class ProductDaoWithGenDao extends GenericDao<Product> implements IProductDao {

	@Override
	public List<Product> findByDescription(String search, Connection cnx) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
