package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";
	private String user = "student";
	private String pass = "student";

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Film findFilmById(int filmId) throws SQLException {
		Connection conn = DriverManager.getConnection(URL, user, pass);
		String sql = "SELECT * FROM film WHERE film.id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, filmId);
		System.out.println(stmt);
		ResultSet rs = stmt.executeQuery();
		Film f = null;
		if (rs.equals(null)) {
			return f;
		}
		while (rs.next()) {
//			System.out.println(rs.getString("id") + " " + rs.getString("title") + " " + rs.getString("description")
//					+ " " + rs.getString("release_year") + " " + rs.getString("language_id") + " "
//					+ rs.getString("rental_duration") + " " + rs.getString("rental_rate") + " " + rs.getString("length")
//					+ " " + rs.getString("replacement_cost") + " " + rs.getString("rating") + " "
//					+ rs.getString("special_features"));
			int id = rs.getInt("id");
			String title = rs.getString("title");
			String description = rs.getString("description");
			Date release_year = rs.getDate("release_year");
			int language_id = rs.getInt("language_id");
			int rental_duration = rs.getInt("rental_duration");
			double rental_rate = rs.getDouble("rental_rate");
			int length = rs.getInt("length");
			double replacement_cost = rs.getDouble("replacement_cost");
			String rating = rs.getString("rating");
			String special_features = rs.getString("special_features");
			f = new Film(id, title, description, release_year, language_id, rental_duration, rental_rate, length,
					replacement_cost, rating, special_features);
		}
		rs.close();
		stmt.close();
		conn.close();
		return f;
	}

	@Override
	public Actor findActorById(int actorId) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = DriverManager.getConnection(URL, user, pass);
		String sql = "SELECT * FROM actor WHERE actor.id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, actorId);     
		System.out.println(stmt);
		ResultSet rs = stmt.executeQuery();
		Actor a = null;
		if (rs.equals(null)) {
			return a;
		}
		while (rs.next()) {
//			System.out.println(rs.getString("id") + " " + rs.getString("title") + " " + rs.getString("description")
//					+ " " + rs.getString("release_year") + " " + rs.getString("language_id") + " "
//					+ rs.getString("rental_duration") + " " + rs.getString("rental_rate") + " " + rs.getString("length")
//					+ " " + rs.getString("replacement_cost") + " " + rs.getString("rating") + " "
//					+ rs.getString("special_features"));
			int id = rs.getInt("id");
			String first_name = rs.getString("first_name");
			String last_name = rs.getString("last_name");
			a = new Actor(id, first_name, last_name);
		}
		rs.close();
		stmt.close();
		conn.close();
		return a;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		// TODO Auto-generated method stub
		return null;
	}

}
