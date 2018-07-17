package com.stackroute.movieapp.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.stackroute.movieapp.services.NextSequenceService;

@Document
//@No
public class Movie {
	@Id
	private int id;
	private String title;
	private String imdbId;
	private String poster;
	private String year;

	public Movie() {

	}

	@Autowired
	NextSequenceService nextSequenceService;
	
	public Movie(int id, String title, String imdbId, String poster, String year) {
		super();
		this.id = id;
		this.title = title;
		this.imdbId = imdbId;
		this.poster = poster;
		this.year = year;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImdbId() {
		return imdbId;
	}

	public void setImdbId(String imdbId) {
		this.imdbId = imdbId;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "Movie [title=" + title + ", imdbId=" + imdbId + ", poster=" + poster + ", year=" + year + "]";
	}

}