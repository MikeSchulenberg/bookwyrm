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
	
	/**
	 * 
	 * @param model The object carrying the application's data.
	 * @return A view listing all Books in the database.
	 */
	@GetMapping("/all")
	public String findAll(Model model) {	
		List<Book> books = bookService.findAll();
		
		model.addAttribute("books", books);
		
		return "/books/all-books";
	}

	/**
	 * @param bookID The primary key of a Book to search for in the database.
	 * @param model The object carrying the application's data.
	 * @return A view showing the details of a single Book.
	 */
	@GetMapping("/{bookID}")
	public String getBook(@PathVariable int bookID, Model model) {
		Book book = bookService.findByID(bookID);
		
		model.addAttribute("book", book);
		
		return "/books/book-detail";
	}
	
	/**
	 * @param model The object carrying the application's data.
	 * @return A form for adding a Book to the database.
	 */
	@GetMapping("/add")
	public String showFormForAdd(Model model) {
		Book book = new Book();
		
		model.addAttribute("book", book);
		model.addAttribute("action", "/books/save");
		
		return "/books/book-form";
	}
	
	/**
	 * @param bookID The primary key of a Book to update in the database.
	 * @param model The object carrying the application's data.
	 * @return A form for updating a Book.
	 */
	@GetMapping("/update")
	public String showFormForUpdate(@RequestParam("bookID") int bookID,
									Model model) {
		
		Book book = bookService.findByID(bookID);
		
		model.addAttribute("book", book);
		model.addAttribute("action", "/books/save");
		
		return "/books/book-form";
	}
	
	/**
	 * @param book A Book to be saved in the database.
	 * @return For new Books, the route that allows users to add an Author.
	 * For existing books, the route that loads the view listing all Books
	 * in the database.
	 */
	@PostMapping("/save")
	public String saveBook(@ModelAttribute("book") Book book) {
		boolean isNew = book.isNew();
		
		bookService.save(book);
		
		if (isNew) {
			return "redirect:/books/" + book.getId() + "/add-author";
		}
		
		else {
			return "redirect:/books/all";
		}	
	}
	
	/**
	 * @param bookID The primary key of a Book to delete from the database.
	 * @return The route that loads the view listing all Books in the database.
	 */
	@GetMapping("/delete")
	public String delete(@RequestParam("bookID") int bookID) {
		bookService.deleteByID(bookID);
		
		return "redirect:/books/all";
	}
	
	/**
	 * @param bookID The primary key of a Book to which an Author should
	 * be added.
	 * @param model The object carrying the application's data.
	 * @return A form for adding an Author to the database.
	 */
	@GetMapping("/{bookID}/add-author")
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
	@PostMapping("/{bookID}/add-author")
	public String saveAuthorToBook(@PathVariable("bookID") int bookID,
								   @ModelAttribute Author author,
								   Model model) {
		
		Book book = bookService.findByID(bookID);
		bookService.saveAuthorToBook(book, author);

		model.addAttribute("book", book);
		
		return "redirect:/books/update?bookID=" + book.getId();
	}
	
}
