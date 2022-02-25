package com.example.myfirstprojectspring;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class MovieService {
	
	static List<Movie>  movies = new ArrayList<Movie>(); 
	static {
		//String title, int year
		Movie movie1 = new Movie ("Die Hard", 1982 );
		Movie movie2 = new Movie ("Casablanca", 1962 );
		Movie movie3 = new Movie ("Apocalypse", 1987 );
	
		movies.add(movie1);
		movies.add(movie2);
		movies.add(movie3);
	
	}
	public List<Movie> queryMovie(){
		
		System.out.println ("Movies" + movies);
		
		return movies;
	}

}
