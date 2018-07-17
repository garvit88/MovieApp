package com.stackroute.movieapp.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.movieapp.domain.Movie;
import com.stackroute.movieapp.exceptions.MovieAlreadyExistsException;
import com.stackroute.movieapp.exceptions.MovieNotFoundException;
import com.stackroute.movieapp.repositories.MovieRepository;

@Service
public class MovieServiceImpl implements MovieService {

	private MovieRepository movieRepository;

	@Autowired
	NextSequenceService nextSequenceService;

	@Autowired
	public MovieServiceImpl(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	@Override
	public Movie saveMovie(Movie movie) throws MovieAlreadyExistsException {
		System.out.println(movieRepository.getByName(movie.getTitle()));
		if (movieRepository.getByName(movie.getTitle()) == null) {
			throw new MovieAlreadyExistsException("Movie Already Exists");
		}
		movie.setId(nextSequenceService.getNextSequence("counter"));
		return movieRepository.save(movie);

	}

	@Override
	public Iterable<Movie> getAllMovies() {
		return movieRepository.findAll();
	}

	@Override
	public boolean deleteMovie(int id) {
		try {
			movieRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	@Override
	public Movie updateMovie(Movie movie, int id) {
		movie.setId(id);
		return movieRepository.save(movie);
	}

	@Override
	public Optional<Movie> getMovieById(int id) throws MovieNotFoundException {
		if (!movieRepository.existsById(id))
			throw new MovieNotFoundException("Movie not Found");
		return movieRepository.findById(id);
	}

	@Override
	public Iterable<Movie> getByName(String name) {
		return movieRepository.getByName(name);
	}

}