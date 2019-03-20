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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mikeschulenbergdev.bookwyrm.entity.Author;
import com.mikeschulenbergdev.bookwyrm.service.AuthorService;

/**
 * Controller to handle Author objects.
 * 
 * @author Mike Schulenberg
 * @version 0.0.1-SNAPSHOT
 *
 */
@Controller
@RequestMapping("/authors")
public class AuthorController {

	private AuthorService authorService;

	@Autowired
	public AuthorController(AuthorService authorService) {
		this.authorService = authorService;
	}
	
	// TODO: update @return comment
	/**
	 * @return A list of objects representing all Authors in the database.
	 */	
	@GetMapping("/all")
	public String findAll(Model model) {
		List<Author> authors = authorService.findAll();
		
		model.addAttribute("authors", authors);
		
		return "/authors/all-authors";
	}

	// TODO: update @return comment
	/**
	 * @param authorID The primary key of an Author to search for in the database.
	 * @return An object representing the Author with a primary key that matches
	 * the `authorID`.
	 */
	@GetMapping("/{authorID}")
	public String getAuthor(@PathVariable int authorID, Model model) {
		Author author = authorService.findByID(authorID);
		
		model.addAttribute("author", author);
		
		return "/authors/author-detail";
	}
	
	// TODO: rework method for MVC controller
	/**
	 * @param author An new object representing the Author to be added to the database.
	 * @return The Author just added to the database.
	 */
	@PostMapping("/authors")
	public Author addAuthor(@RequestBody Author author) {
		/* Force the database to save as a new author, rather than updating
		 * an existing one, by setting its id to 0. */
		author.setId(0);
		authorService.save(author);
		
		return author;
	}
	
	// TODO: rework method for MVC controller
	/**
	 * @param author An object representing the Author to be updated in the database.
	 * @return The Author just updated in the database.
	 */
	@PutMapping("/authors")
	public Author updateAuthor(@RequestBody Author author) {
		authorService.save(author);
		
		return author;
	}
	
	// TODO: rework method for MVC controller
	/**
	 * @param authorID The primary key of an Author to delete from the database.
	 * @return A String identifying the Author just deleted from the database
	 */
	@DeleteMapping("/authors/{authorID}")
	public String deleteAuthor(@PathVariable int authorID) {
		Author author = authorService.findByID(authorID);
		
		if (author == null) {
			throw new RuntimeException("Author ID not found: " + authorID);
		}
		
		authorService.deleteByID(authorID);
		
		return "Deleted Author ID: " + authorID;
	}
	
}
