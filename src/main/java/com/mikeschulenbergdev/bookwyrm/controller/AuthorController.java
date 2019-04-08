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

	/**
	 * @param model The object carrying the application's data.
	 * @return A view listing all Authors in the database.
	 */
	@GetMapping("/all")
	public String findAll(Model model) {
		List<Author> authors = authorService.findAll();
		
		model.addAttribute("authors", authors);
		
		return "/authors/all-authors";
	}
	
	/**
	 * @param authorID The primary key of an Author to search for in the database.
	 * @param model The object carrying the application's data.
	 * @return A view showing the details of a single Author.
	 */
	@GetMapping("/{authorID}")
	public String getAuthor(@PathVariable int authorID, Model model) {
		Author author = authorService.findByID(authorID);
		
		model.addAttribute("author", author);
		
		return "/authors/author-detail";
	}
	
	/**
	 * @param model The object carrying the application's data.
	 * @return A form for adding an Author to the database.
	 */
	@GetMapping("/add")
	public String showFormForAdd(Model model) {
		Author author = new Author();
		
		model.addAttribute("author", author);
		model.addAttribute("action", "/authors/save");
		
		return "/authors/author-form";
	}
	
	/**
	 * @param authorID The primary key of an Author to update in the database.
	 * @param model The object carrying the application's data.
	 * @return A form for updating an Author.
	 */
	@GetMapping("/update")
	public String showFormForUpdate(@RequestParam("authorID") int authorID, 
									Model model) {
		
		Author author = authorService.findByID(authorID);
		
		model.addAttribute("author", author);
		
		return "/authors/author-form";
	}
	
	/**
	 * @param author An Author to be saved in the database.
	 * @return The route that loads the view listing all Authors in the database.
	 */
	@PostMapping("/save")
	public String saveAuthor(@ModelAttribute("author") Author author) {
		authorService.save(author);
		
		return "redirect:/authors/all";
	}
	
	/**
	 * @param authorID The primary key of an Author to delete from the database.
	 * @return The route that loads the view listing all Authors in the database.
	 */
	@GetMapping("/delete")
	public String delete(@RequestParam("authorID") int authorID) {	
		authorService.deleteByID(authorID);
		
		return "redirect:/authors/all";
	}
	
}
