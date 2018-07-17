package com.stackroute.movieapp.repositories;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.movieapp.domain.Movie;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Integer> {

	// @Query(value = "Select m From Movie m Where m.title =?0")
	@Query("{ title : ?0 }")
	public Iterable<Movie> getByName(String name);
}