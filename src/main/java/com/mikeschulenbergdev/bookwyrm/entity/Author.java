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

import java.util.ArrayList;
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
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * This class represents an author of one or more books.
 * 
 * @author Mike Schulenberg
 * @version 0.0.1-SNAPSHOT
 *
 */
@Entity
@Table(name="author")
public class Author {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="middle_name")
	private String middleName;
	
	@Column(name="last_name")
	private String lastName;
	
	@ManyToMany(fetch=FetchType.LAZY,
			    cascade= {CascadeType.DETACH, CascadeType.MERGE,
			    		  CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(
			name="book_author",
			joinColumns=@JoinColumn(name="author_id"),
			inverseJoinColumns=@JoinColumn(name="book_id")
			)
	@OrderBy("title")
	private List<Book> books = new ArrayList<>();
	
	public Author() {
		
	}

	public Author(String firstName, String middleName, String lastName) {
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
	}

	/**
	 * @return The primary key used to identify the Author in the database.
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id The primary key used to identify the Author in the database.
	 */
	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public void addBook(Book book) {		
		books.add(book);
		book.getAuthor().add(this);
	}
	
	public void removeBook(Book book) {
		books.remove(book);
		book.getAuthor().remove(this);
	}
	
	@Override
	public String toString() {
		return "Author [id=" + id + ", firstName=" + firstName + ", middleName=" + 
				middleName + ", lastName=" + lastName + "]";
	}

}
