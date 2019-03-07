/*******************************************************************************
 * Copyright (C) 2019 Mike Schulenberg
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/

package com.mikeschulenbergdev.bookwyrm.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * This class represents a single book.
 * 
 * @author Mike Schulenberg
 * @version 0.0.1-SNAPSHOT
 *
 */
@Entity
@Table(name="book")
public class Book {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="genre")
	private String genre;
	
	@Column(name="series_name")
	private String seriesName;
	
	@Column(name="series_number")
	private int seriesNumber;
	
//	@ManyToMany(fetch=FetchType.LAZY,
//				cascade= {CascadeType.DETACH, CascadeType.MERGE,
//						  CascadeType.PERSIST, CascadeType.REFRESH})
//	@JoinTable(
//			name="book_author",
//			joinColumns=@JoinColumn(name="book_id"),
//			inverseJoinColumns=@JoinColumn(name="author_id")
//			)
//	private List<Author> author;
	
	public Book() {
		
	}

	public Book(String title, String genre, String seriesName, int seriesNumber) {
		this.title = title;
		this.genre = genre;
		this.seriesName = seriesName;
		this.seriesNumber = seriesNumber;
	}

	/**
	 * @return The primary key used to identify the Book in the database.
	 */
	public int getId() {
		return id;
	}

	/**
	 * 
	 * @param id The primary key used to identify the Book in the database.
	 */
	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	// TODO: get List<Author> working and update toString()
	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", genre=" + genre + ", seriesName=" + seriesName
				+ ", seriesNumber=" + seriesNumber + "]";
	}
	
//	public List<Author> getAuthor() {
//		return author;
//	}
//
//	public void setAuthor(List<Author> author) {
//		this.author = author;
//	}
//
//	@Override
//	public String toString() {
//		return "Book [id=" + id + ", title=" + title + ", genre=" + genre 
//				+ ", seriesName=" + seriesName + ", seriesNumber=" 
//				+ seriesNumber + ", author=" + author + "]";
//	}
	
}
