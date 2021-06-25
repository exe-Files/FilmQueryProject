package com.skilldistillery.filmquery.app;

import java.awt.List;
import java.sql.SQLException;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) throws SQLException {
		FilmQueryApp app = new FilmQueryApp();
//    app.test();
		app.launch();
	}

	private void test() throws SQLException {
		Film film = db.findFilmById(13);
		System.out.println(film);
	}

	private void launch() throws SQLException {
		Scanner input = new Scanner(System.in);

		startUserInterface(input);

		input.close();
	}

	private void startUserInterface(Scanner input) throws SQLException {
		System.out.println("What option would you like:");
		System.out.println("1. Look up a film by its id");
		System.out.println("2. Look up a film by a search keyword.");
		System.out.println("3. Exit the application.");
		// try catch block here
		int uI = input.nextInt();
		System.out.println("You entered " + uI);
		
		switch (uI) {
		case 1:
			lookUpFilmByID(input);
			break;
		case 2:
			lookUpFilmByKeyword(input);
			break;
		case 3:
			break;
		default:

		}

	}

	private void lookUpFilmByID(Scanner input) throws SQLException {
		System.out.println("Please enter the film ID you would like to search for: ");
		Film film = db.findFilmById(input.nextInt());
		System.out.println(film);
	}

	private void lookUpFilmByKeyword(Scanner input) throws SQLException {
		System.out.println("Please enter the search term for the film are looking for: ");
		Film film = db.findFilmByKeyword(input.next());
		System.out.println(film);

	}

}
