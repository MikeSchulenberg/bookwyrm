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

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mikeschulenbergdev.bookwyrm.entity.Book;

/**
 * Implementation for a Data Access Object to handle database transactions
 * for the Book class.
 * 
 * @author Mike Schulenberg
 * @version 0.0.1-SNAPSHOT
 *
 */
@Repository
public class BookDAOImpl implements BookDAO {

	private EntityManager entityManager;
	
	@Autowired
	public BookDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Book> findAll() {
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query<Book> query = currentSession.createQuery("from Book", Book.class);
		List<Book> books = query.getResultList();

		return books;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Book findByID(int id) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		Book book = currentSession.get(Book.class, id);
		
		return book;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void save(Book book) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		currentSession.saveOrUpdate(book);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteByID(int id) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query<?> query =
				currentSession.createQuery("delete from book where id=:bookID");
		query.setParameter("bookID", id);
		
		query.executeUpdate();
	}

}
