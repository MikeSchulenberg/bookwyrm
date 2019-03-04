package com.mikeschulenbergdev.bookwyrm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="book")
public class Book {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="author")
	private String author;
	
	@Column(name="genre")
	private String genre;
	
	@Column(name="series_name")
	private String seriesName;
	
	@Column(name="series_number")
	private int seriesNumber;
	
	public Book() {
		
	}

	public Book(String title, String author, String genre, String seriesName, int seriesNumber) {
		this.title = title;
		this.author = author;
		this.genre = genre;
		this.seriesName = seriesName;
		this.seriesNumber = seriesNumber;
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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getSeriesName() {
		return seriesName;
	}

	public void setSeriesName(String seriesName) {
		this.seriesName = seriesName;
	}

	public int getSeriesNumber() {
		return seriesNumber;
	}

	public void setSeriesNumber(int seriesNumber) {
		this.seriesNumber = seriesNumber;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author 
				+ ", genre=" + genre + ", seriesName=" + seriesName 
				+ ", seriesNumber=" + seriesNumber + "]";
	}
	
}
