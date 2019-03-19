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

import com.mikeschulenbergdev.bookwyrm.entity.Author;

/**
 * Implementation for a Data Access Object to handle database transactions
 * for the Author class.
 * 
 * @author Mike Schulenberg
 * @version 0.0.1-SNAPSHOT
 *
 */
@Repository
public class AuthorDAOImpl implements AuthorDAO {

	private EntityManager entityManager;
	
	@Autowired
	public AuthorDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Author> findAll() {
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query<Author> query = currentSession.createQuery("from Author order by lastName", Author.class);	
		List<Author> authors = query.getResultList();
		
		return authors;
	}

	@Override
	public Author findByID(int id) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		Author author = currentSession.get(Author.class, id);
		
		return author;
	}

	@Override
	public void save(Author author) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		currentSession.saveOrUpdate(author);
	}

	@Override
	public void deleteByID(int id) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query<?> query = 
				currentSession.createQuery("delete from Author where id=:authorID");
		query.setParameter("authorID", id);
		
		query.executeUpdate();
	}

}
