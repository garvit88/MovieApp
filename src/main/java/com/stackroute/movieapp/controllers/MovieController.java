package com.stackroute.movieapp.controllers;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.movieapp.domain.Movie;
import com.stackroute.movieapp.exceptions.MovieAlreadyExistsException;
import com.stackroute.movieapp.exceptions.MovieNotFoundException;
import com.stackroute.movieapp.services.MovieService;

@RestController
@RequestMapping("/api/v1")
public class MovieController {

	private MovieService movieService;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	MovieController(MovieService movieService) {
		this.movieService = movieService;
	}

	@PostMapping("/movie")
	public ResponseEntity<?> saveMovie(@RequestBody Movie movie) {

		try {
			logger.debug("This is a debug message");
			Movie savedMovie = movieService.saveMovie(movie);
			return new ResponseEntity<Movie>(savedMovie, HttpStatus.CREATED);
		} catch (MovieAlreadyExistsException e) {
			return new ResponseEntity<String>("Already exists!", HttpStatus.CONFLICT);
		}

	}

	@GetMapping("/movies")
	public ResponseEntity<?> getAllMovie() {
		return new ResponseEntity<Iterable<Movie>>(movieService.getAllMovies(), HttpStatus.OK);
	}

	@GetMapping("/movie/{id}")
	public ResponseEntity<?> getMovieById(@PathVariable int id) {

		try {
			Optional<Movie> movie = movieService.getMovieById(id);
			return new ResponseEntity<Optional<Movie>>(movie, HttpStatus.OK);
		} catch (MovieNotFoundException e) {
			return new ResponseEntity<String>("Not Found", HttpStatus.CONFLICT);
		}
	}

	@DeleteMapping("/movie/{id}")
	public ResponseEntity<?> deleteMovieById(@PathVariable int id) {
		movieService.deleteMovie(id);
		return new ResponseEntity<String>("Deleted", HttpStatus.OK);
	}

	@PutMapping("/movie/{id}")
	public ResponseEntity<?> updateMovie(@PathVariable int id, @RequestBody Movie movie) {
		return new ResponseEntity<Movie>(movieService.updateMovie(movie, id), HttpStatus.OK);
	}

	@GetMapping("/moviename/{title}")
	public ResponseEntity<?> getMovieById(@PathVariable String title) {
		return new ResponseEntity<Iterable<Movie>>(movieService.getByName(title), HttpStatus.OK);
	}
}