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

package com.mikeschulenbergdev.bookwyrm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mikeschulenbergdev.bookwyrm.entity.Author;
import com.mikeschulenbergdev.bookwyrm.entity.Book;
import com.mikeschulenbergdev.bookwyrm.service.BookService;

/**
 * Controller to handle Book objects.
 * 
 * @author Mike Schulenberg
 * @version 0.0.1-SNAPSHOT
 *
 */
@Controller
@RequestMapping("/books")
public class BookController {

	private BookService bookService;

	@Autowired
	public BookController(BookService bookService) {
		this.bookService = bookService;
	}
	
	// TODO: update comments
	/**
	 * @return A list of objects representing all Books in the database.
	 */	
	@GetMapping("/all")
	public String findAll(Model model) {	
		List<Book> books = bookService.findAll();
		
		model.addAttribute("books", books);
		
		return "/books/all-books";
	}

	// TODO: update comments
	/**
	 * @param bookID The primary key of a Book to search for in the database.
	 * @return An object representing the Book with a primary key that matches
	 * the `bookID`.
	 */
	@GetMapping("/{bookID}")
	public String getBook(@PathVariable int bookID, Model model) {
		Book book = bookService.findByID(bookID);
		
		model.addAttribute("book", book);
		
		return "/books/book-detail";
	}
	
	// TODO: update comments
	/**
	 * @param book An new object representing the Book to be added to the database.
	 * @return The Book just added to the database.
	 */
	@GetMapping("/add")
	public String showFormForAdd(Model model) {
		Book book = new Book();
		
		model.addAttribute("book", book);
		
		return "/books/book-form";
	}
	
	// TODO: update comments
	/**
	 * @param book An object representing the Book to be updated in the database.
	 * @return The Book just updated in the database.
	 */
	@GetMapping("/update")
	public String showFormForUpdate(@RequestParam("bookID") int id,
									Model model) {
		
		Book book = bookService.findByID(id);
		
		model.addAttribute("book", book);
		
		return "/books/book-form";
	}
	
	/**
	 * 
	 * @param book An object representing the Book to be saved in the database.
	 * @return The web page to load after saving the Book.
	 */
	@PostMapping("/save")
	public String saveBook(@ModelAttribute("book") Book book) {
		bookService.save(book);
		
		return "redirect:/books/all";
	}
	
	// TODO: update comments
	/**
	 * @param bookID The primary key of a Book to delete from the database.
	 * @return A String identifying the Book just deleted from the database
	 */
	@GetMapping("/delete")
	public String delete(@RequestParam("bookID") int id) {
		bookService.deleteByID(id);
		
		return "redirect:/books/all";
	}
	
	// TODO: write comment
	@PostMapping("/add-author")
	public String showAuthorFormForAdd(@ModelAttribute Book book,
									   Model model) {
		
		bookService.save(book);
		
		Author author = new Author();
		
		model.addAttribute("author", author);
		model.addAttribute("action", "/books/" + book.getId() + "/save-author");
		
		return "/authors/author-form";
	}
	
	// TODO: write comment
	@PostMapping("/{bookID}/save-author")
	public String saveAuthorToBook(@PathVariable("bookID") int bookID,
								   @ModelAttribute Author author,
								   Model model) {
		
		Book book = bookService.findByID(bookID);
		bookService.saveAuthorToBook(book, author);

		model.addAttribute("book", book);
		
		return "redirect:/books/update?bookID=" + book.getId();
	}
	
	// TODO: add findByGenre() method
	// TODO: add findBySeries() method
	
}
