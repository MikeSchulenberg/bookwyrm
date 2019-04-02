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

import com.mikeschulenbergdev.bookwyrm.dao.BookDAO;
import com.mikeschulenbergdev.bookwyrm.entity.Author;
import com.mikeschulenbergdev.bookwyrm.entity.Book;

/**
 * Implementation for a Service to handle interactions between the
 * BookRestController and BookDAO classes.
 * 
 * @author Mike Schulenberg
 * @version 0.0.1-SNAPSHOT
 *
 */
@Service
public class BookServiceImpl implements BookService {

	private BookDAO bookDAO;
	
	@Autowired
	public BookServiceImpl(BookDAO bookDAO) {
		this.bookDAO = bookDAO;
	}

	@Override
	@Transactional
	public List<Book> findAll() {
		return bookDAO.findAll();
	}

	@Override
	@Transactional
	public Book findByID(int id) {
		return bookDAO.findByID(id);
	}

	@Override
	@Transactional
	public void save(Book book) {
		bookDAO.save(book);
	}

	@Override
	@Transactional
	public void deleteByID(int id) {
		bookDAO.deleteByID(id);
	}

	@Override
	public Book saveAuthorToBook(Book book, Author author) {
		book.addAuthor(author);
		
		save(book);
		
		System.out.println(">>> BookService.saveAuthorToBook() - " + book.getAuthors());
		System.out.println(">>> BookService.saveAuthorToBook() - " + author.getBooks());

		return book;
	}
	
}
