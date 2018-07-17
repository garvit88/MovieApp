package com.stackroute.movieapp.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.stackroute.movieapp.domain.Movie;
import com.stackroute.movieapp.exceptions.MovieAlreadyExistsException;
import com.stackroute.movieapp.exceptions.MovieNotFoundException;

@Service
public interface MovieService {

	public Movie saveMovie(Movie movie) throws MovieAlreadyExistsException;
	public Iterable<Movie> getAllMovies();
	public boolean deleteMovie(int id);
	public Movie updateMovie(Movie movie,int id);
	public Optional<Movie> getMovieById(int id) throws MovieNotFoundException;
	public Iterable<Movie> getByName(String name);
}