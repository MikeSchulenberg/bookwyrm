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

package com.mikeschulenbergdev.bookwyrm.dao;

import java.util.List;

import com.mikeschulenbergdev.bookwyrm.entity.Author;

/**
 * Interface for a Data Access Object to handle database transactions
 * for the Author class.
 * 
 * @author Mike Schulenberg
 * @version 0.0.1-SNAPSHOT
 *
 */
public interface AuthorDAO {

	/**
	 * This function is called from the application's Service layer.
	 * @see com.mikeschulenbergdev.bookwyrm.service.AuthorService#findAll()
	 * 
	 * @return As per AuthorService.findAll()
	 */
	public List<Author> findAll();
	
	/** 
	 * This function is called from the application's Service layer.
	 * @see com.mikeschulenbergdev.bookwyrm.service.AuthorService#findByID(int)
	 * 
	 * @param id As per AuthorService.findByID(int)
	 * @return As per AuthorService.findByID(int)
	 */
	public Author findByID(int id);

	/**
	 * This function is called from the application's Service layer.
	 * @see com.mikeschulenbergdev.bookwyrm.service.AuthorService#save(Author)
	 * 
	 * @param author As per AuthorService.save(Author)
	 */
	public void save(Author author);
	
	/**
	 * This function is called from the application's Service layer.
	 * @see com.mikeschulenbergdev.bookwyrm.service.AuthorService#deleteByID(int)
	 * 
	 * @param id As per AuthorService.deleteByID(int)
	 */
	public void deleteByID(int id);
	
}
