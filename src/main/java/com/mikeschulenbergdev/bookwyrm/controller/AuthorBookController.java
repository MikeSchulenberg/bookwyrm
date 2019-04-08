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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.mikeschulenbergdev.bookwyrm.entity.Author;
import com.mikeschulenbergdev.bookwyrm.entity.Book;
import com.mikeschulenbergdev.bookwyrm.service.AuthorBookService;

/**
 * Controller to handle operations involving Author and Book objects
 * together.
 * 
 * @author Mike Schulenberg
 * @version 0.0.1-SNAPSHOT
 *
 */
@Controller
public class AuthorBookController {

	AuthorBookService authorBookService;
	
	@Autowired
	public AuthorBookController(AuthorBookService authorBookService) {
		this.authorBookService = authorBookService;
	}
	
	/**
	 * @param authorID The primary key of an Author to which a Book should
	 * be added.
	 * @param model The object carrying the application's data.
	 * @return A form for adding a Book to the database.
	 */
	@GetMapping("/authors/{authorID}/add-book")
	public String showAddBookForm(@PathVariable("authorID") int authorID,
								  Model model) {
		
		Book book = new Book();
		
		model.addAttribute("book", book);
		model.addAttribute("action", "/authors/" + authorID + "/add-book");
		
		return "/books/book-form";
	}
	
	/**
	 * @param authorID The primary key of an Author to which a Book should
	 * be added.
	 * @param book The Book to add to the author.
	 * @param model The object carrying the application's data.
	 * @return The route that loads the form for updating the Book further, 
	 * either with additional Authors or the Book data itself.
	 */
	@PostMapping("/authors/{authorID}/add-book")
	public String saveBookToAuthor(@PathVariable("authorID") int authorID,
								   @ModelAttribute("book") Book book,
								   Model model) {
		
		Author author = authorBookService.findAuthorByID(authorID);
		authorBookService.saveAuthorAndBook(author, book);
		
		model.addAttribute("book", book);
		
		return "redirect:/books/update?bookID=" + book.getId();
	}
	
	/**
	 * @param bookID The primary key of a Book to which an Author should
	 * be added.
	 * @param model The object carrying the application's data.
	 * @return A form for adding an Author to the database.
	 */
	@GetMapping("/books/{bookID}/add-author")
	public String showAddAuthorForm(@PathVariable("bookID") int bookID,
									Model model) {
		
		Author author = new Author();
		
		model.addAttribute("author", author);
		model.addAttribute("action", "/books/" + bookID + "/add-author");
		
		return "/authors/author-form";
	}
	
	/**
	 * @param bookID The primary key of a Book to which an Author should
	 * be added.
	 * @param author The Author to add to the Book.
	 * @param model The object carrying the application's data.
	 * @return The route that loads the form for updating the Book further, 
	 * either with additional Authors or the Book data itself.
	 */
	@PostMapping("/books/{bookID}/add-author")
	public String saveAuthorToBook(@PathVariable("bookID") int bookID,
								   @ModelAttribute Author author,
								   Model model) {
		
		Book book = authorBookService.findBookByID(bookID);
		authorBookService.saveAuthorAndBook(author, book);

		model.addAttribute("book", book);
		
		return "redirect:/books/update?bookID=" + book.getId();
	}
	
}
