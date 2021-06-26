package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
//		System.out.println(stmt);
		ResultSet rs = stmt.executeQuery();
		Film f = null;
		if (rs.equals(null)) {
			return null;
		}
		while (rs.next()) {
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
			// instantiates a new instance of Film based on the constructor
			f = new Film(id, title, description, release_year, language_id, rental_duration, rental_rate, length,
					replacement_cost, rating, special_features);
			f.setActors(findActorsByFilmId(filmId));
		}
		rs.close();
		stmt.close();
		conn.close();
		return f; //returns ONE film object based on the Film ID
	}

	@Override
	public List<Film> findFilmByKeyword(String keyWord) throws SQLException {
		Connection conn = DriverManager.getConnection(URL, user, pass);
		String sql = "SELECT * FROM film WHERE film.title LIKE ? OR film.description LIKE ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, "%" + keyWord + "%");
		stmt.setString(2, "%" + keyWord + "%");
//		System.out.println(stmt);
		ResultSet rs = stmt.executeQuery();
		if (rs.equals(null)) {
			System.out.println("Invalid Query");
			return null;
		}
		List<Film> films = new ArrayList<Film>();
		while (rs.next()) {
			Film film = parseResultSet(rs);
			films.add(film);
		}
		return films; //returns a List of film objects
	}

	public Film parseResultSet(ResultSet rs) throws SQLException {
		Film film = new Film();
		film.setId(rs.getInt("id"));
		film.setTitle(rs.getString("title"));
		film.setDescription(rs.getString("description"));
		film.setRelease_year(rs.getDate("release_year"));
		film.setLanguage_id(rs.getInt("language_id"));
		film.setRental_duration(rs.getInt("rental_duration"));
		film.setRental_rate(rs.getDouble("rental_rate"));
		film.setLength(rs.getInt("length"));
		film.setReplacement_cost(rs.getDouble("replacement_cost"));
		film.setRating(rs.getString("rating"));
		film.setSpecial_features(rs.getString("special_features"));
		film.setActors(findActorsByFilmId(rs.getInt("id")));
		film.setLanguage_id(0);
		return film;

	}

	@Override
	public Actor findActorById(int actorId) throws SQLException {
		Connection conn = DriverManager.getConnection(URL, user, pass);
		String sql = "SELECT * FROM actor WHERE actor.id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, actorId);
		ResultSet rs = stmt.executeQuery();
		Actor a = null;
		if (rs.equals(null)) {
			return null;
		}
		while (rs.next()) {
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
	public List<Actor> findActorsByFilmId(int filmId) throws SQLException {
		Connection conn = DriverManager.getConnection(URL, user, pass);
		String sql = "SELECT film.title, actor.id, actor.first_name, actor.last_name "
				+ "FROM film JOIN film_actor ON film.id = film_actor.film_id"
				+ " JOIN actor ON film_actor.actor_id = actor.id " + "WHERE film.id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, filmId);
//		System.out.println(stmt);
		ResultSet rs = stmt.executeQuery();
		if (rs.equals(null)) {
			return null;
		}
		List<Actor> actorList = new ArrayList<Actor>();
		while (rs.next()) {
			actorList.add(findActorById(rs.getInt("id"))); // should cycle through rs actors and add to the list
//			System.out.println(
//					rs.getString("title") + " " + rs.getString("first_name") + " " + rs.getString("last_name"));
		}
		return actorList;
	}

}
