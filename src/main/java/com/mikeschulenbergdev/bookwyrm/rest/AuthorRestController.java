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

import com.mikeschulenbergdev.bookwyrm.entity.Author;
import com.mikeschulenbergdev.bookwyrm.service.AuthorService;

/**
 * REST controller to handle Author objects.
 * 
 * @author Mike Schulenberg
 * @version 0.0.1-SNAPSHOT
 *
 */
@RestController
@RequestMapping("/api")
public class AuthorRestController {

	private AuthorService authorService;

	@Autowired
	public AuthorRestController(AuthorService authorService) {
		this.authorService = authorService;
	}
	
	@GetMapping("/authors")
	public List<Author> findAll() {
		return authorService.findAll();
	}
	
	@GetMapping("/authors/{authorID}")
	public Author getAuthor(@PathVariable int authorID) {
		Author author = authorService.findByID(authorID);
		
		if (author == null) {
			throw new RuntimeException("Author ID not found: " + authorID);
		}
		
		return author;
	}
	
	@PostMapping("/authors")
	public Author addAuthor(@RequestBody Author author) {
		/* Force the database to save as a new author, rather than updating
		 * an existing one, by setting its id to 0. */
		author.setId(0);
		authorService.save(author);
		
		return author;
	}
	
	@PutMapping("/authors")
	public Author updateAuthor(@RequestBody Author author) {
		authorService.save(author);
		
		return author;
	}
	
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
