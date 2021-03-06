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

package com.mikeschulenbergdev.bookwyrm.service;

import com.mikeschulenbergdev.bookwyrm.entity.Author;
import com.mikeschulenbergdev.bookwyrm.entity.Book;

/**
 * Interface for a Service to handle interactions between the
 * AuthorBookController and AuthorDAO class.
 * 
 * @author Mike Schulenberg
 * @version 0.0.1-SNAPSHOT
 *
 */
public interface AuthorBookService {

	/**
	 * @param id The primary key of an Author to search for in the database.
	 * @return An object representing the Author matching the `id`.
	 */	
	public Author findAuthorByID(int id);
	
	/**
	 * @param id The primary key of a Book to search for in the database.
	 * @return An object representing the Book matching the `id`.
	 */	
	public Book findBookByID(int id);
	
	/**
	 * @param author An Author to be associated with a Book and saved 
	 * in the database.
	 * @param book A Book to be associated with an Author and saved.
	 */
	public void saveAuthorAndBook(Author author, Book book);
	
}
