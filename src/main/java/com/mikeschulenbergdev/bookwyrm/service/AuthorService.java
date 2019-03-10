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

import java.util.List;

import com.mikeschulenbergdev.bookwyrm.entity.Author;

/**
 * Interface for a Service to handle interactions between the
 * AuthorRestController and AuthorDAO classes.
 * 
 * @author Mike Schulenberg
 * @version 0.0.1-SNAPSHOT
 *
 */
public interface AuthorService {

	/**
	 * @return A list of objects representing all Authors in the database.
	 */	
	public List<Author> findAll();
	
	/**
	 * @param id The primary key of an Author to search for in the database.
	 * @return An object representing the Author matching the `id`.
	 */	
	public Author findByID(int id);
	
	/**
	 * @param author An object representing the Author to be saved in the database.
	 */	
	public void save(Author author);
	
	/**
	 * @param id The primary key of the Author to delete from the database.
	 */	
	public void deleteByID(int id);
	
}
