package exercice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import exercice.modele.Film;

public class FilmDao implements IFilmDao {

	@Override
	public List<Film> findAll(Connection cnx) throws SQLException {
		List<Film> list = new ArrayList<Film>();

		PreparedStatement ps = cnx.prepareStatement("SELECT * from film " + "ORDER BY id");
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			Film p = new Film();
			p.setId(rs.getLong("id"));
			p.setTitle(rs.getString("title"));
			p.setWatched(rs.getBoolean("watched"));
			if (rs.getObject("rating") == null) {
				p.setRating(null);
			} else {
				p.setRating(rs.getInt("rating"));
			}
			list.add(p);
		}

		rs.close();

		return list;
	}

	@Override
	public List<Film> findAll(int page, int maxByPage, Connection cnx) throws SQLException {
		List<Film> lp = new ArrayList<Film>();
		PreparedStatement ps = cnx
				.prepareStatement("SELECT id, title, watched, rating FROM film ORDER BY id OFFSET ? LIMIT ?");
		ps.setInt(1, maxByPage * page);
		ps.setInt(2, maxByPage);
		ResultSet res = ps.executeQuery();
		while (res.next()) {
			if (res.getObject("rating") == null) {
				lp.add(new Film(res.getLong("id"), res.getString("title"), res.getBoolean("watched"), null));
			} else {
				lp.add(new Film(res.getLong("id"), res.getString("title"), res.getBoolean("watched"),
						res.getInt("rating")));
			}
		}
		res.close();

		return lp;
	}

	@Override
	public Film findById(long id, Connection cnx) throws SQLException {
		Film p = new Film();
		PreparedStatement ps = cnx.prepareStatement("SELECT id, title, watched, rating FROM film WHERE id = ?");
		ps.setLong(1, id);
		ResultSet res = ps.executeQuery();
		while (res.next()) {
			if (res.getObject("rating") == null) {
				p = new Film(res.getLong("id"), res.getString("title"), res.getBoolean("watched"), null);
			} else {
				p = new Film(res.getLong("id"), res.getString("title"), res.getBoolean("watched"),
						res.getInt("rating"));
			}
		}
		res.close();
		return p;
	}

	@Override
	public Film insert(Film p, Connection cnx) throws SQLException {
		int res = 0;
		PreparedStatement ps = cnx.prepareStatement("INSERT INTO film (title, watched, rating) VALUES(?,?,?)",
				Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, p.getTitle());
		ps.setBoolean(2, p.isWatched());
		if (p.getRating() != null)
			ps.setInt(3, p.getRating());
		else {
			ps.setNull(3, Types.INTEGER);
		}
		res = ps.executeUpdate();
		if (res != 0) {
			try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					p.setId(generatedKeys.getLong(1));
				}
			}
		}
		return p;
	}

	@Override
	public int update(Film p, Connection cnx) throws SQLException {
		int res = 0;
		PreparedStatement ps = cnx.prepareStatement("UPDATE film SET title=?, watched=?, rating=? WHERE id=?");
		ps.setString(1, p.getTitle());
		ps.setBoolean(2, p.isWatched());
		if (p.getRating() != null)
			ps.setInt(3, p.getRating());
		else {
			ps.setNull(3, Types.INTEGER);
		}
		ps.setLong(4, p.getId());
		res = ps.executeUpdate();

		return res;
	}

	@Override
	public int delete(long id, Connection cnx) throws SQLException {
		int res = 0;
		PreparedStatement ps = cnx.prepareStatement("DELETE FROM film WHERE id=?");
		ps.setLong(1, id);
		res = ps.executeUpdate();

		return res;
	}

	@Override
	public long count(Connection cnx) throws SQLException {
		long nb = 0;
		PreparedStatement ps = cnx.prepareStatement("SELECT COUNT(id) FROM film");
		ResultSet res = ps.executeQuery();
		if (res.next()) {
			nb = res.getLong(1);
		}
		res.close();
		return nb;
	}

}
