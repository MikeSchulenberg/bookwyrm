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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mikeschulenbergdev.bookwyrm.dao.AuthorDAO;
import com.mikeschulenbergdev.bookwyrm.entity.Author;

/**
 * Implementation for a Service to handle interactions between the
 * AuthorController and AuthorDAO classes.
 * 
 * @author Mike Schulenberg
 * @version 0.0.1-SNAPSHOT
 *
 */
@Service
public class AuthorServiceImpl implements AuthorService {

	private AuthorDAO authorDAO;
	
	@Autowired
	public AuthorServiceImpl(AuthorDAO authorDAO) {
		this.authorDAO = authorDAO;
	}
	
	@Override
	@Transactional
	public List<Author> findAll() {
		return authorDAO.findAll();
	}

	@Override
	@Transactional
	public Author findByID(int id) {
		return authorDAO.findByID(id);
	}

	@Override
	@Transactional
	public void save(Author author) {
		authorDAO.save(author);
	}

	@Override
	@Transactional
	public void deleteByID(int id) {
		authorDAO.deleteByID(id);
	}

}
