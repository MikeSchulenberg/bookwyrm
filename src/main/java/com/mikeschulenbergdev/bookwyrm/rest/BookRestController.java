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

package com.mikeschulenbergdev.bookwyrm.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mikeschulenbergdev.bookwyrm.entity.Book;
import com.mikeschulenbergdev.bookwyrm.service.BookService;

/**
 * REST controller to handle Book objects.
 * 
 * @author Mike Schulenberg
 * @version 0.0.1-SNAPSHOT
 *
 */
@RestController
@RequestMapping("/api")
public class BookRestController {

	private BookService bookService;

	@Autowired
	public BookRestController(BookService bookService) {
		this.bookService = bookService;
	}
	
	/**
	 * @return A list of objects representing all Books in the database.
	 */	
	@GetMapping("/books")
	public List<Book> findAll() {	
		return bookService.findAll();
	}
	
	/**
	 * @param bookID The primary key of a Book to search for in the database.
	 * @return An object representing the Book with a primary key that matches
	 * the `bookID`.
	 */
	@GetMapping("/books/{bookID}")
	public Book getBook(@PathVariable int bookID) {
		Book book = bookService.findByID(bookID);
		
		if (book == null) {
			throw new RuntimeException("Book ID not found: " + bookID);
		}
		
		return book;
	}
	
	/**
	 * @param book An new object representing the Book to be added to the database.
	 * @return The Book just added to the database.
	 */
	@PostMapping("/books")
	public Book addBook(@RequestBody Book book) {
		/* Force the database to save as a new book, rather than updating
		 * an existing one, by setting its id to 0. */
		book.setId(0);
		bookService.save(book);
		
		return book;
	}
	
	/**
	 * @param book An object representing the Book to be updated in the database.
	 * @return The Book just updated in the database.
	 */
	@PutMapping("/books")
	public Book updateBook(@RequestBody Book book) {
		bookService.save(book);
		
		return book;
	}
	
	/**
	 * @param bookID The primary key of a Book to delete from the database.
	 * @return A String identifying the Book just deleted from the database
	 */
	@DeleteMapping("/books/{bookID}")
	public String deleteBook(@PathVariable int bookID) {
		Book book = bookService.findByID(bookID);
		
		if (book == null) {
			throw new RuntimeException("Book ID not found: " + bookID);
		}
		
		bookService.deleteByID(bookID);
		
		return "Deleted Book ID: " + bookID;
	}
	
}
