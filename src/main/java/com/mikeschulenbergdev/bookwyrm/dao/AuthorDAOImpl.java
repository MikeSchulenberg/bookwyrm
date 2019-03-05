package com.mikeschulenbergdev.bookwyrm.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.mikeschulenbergdev.bookwyrm.entity.Author;

public class AuthorDAOImpl implements AuthorDAO {

	private EntityManager entityManager;
	
	@Autowired
	public AuthorDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Author> findAll() {
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query<Author> query = currentSession.createQuery("FROM author", Author.class);	
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
				currentSession.createQuery("DELETE FROM author WHERE id:=authorID");
		query.setParameter("authorID", id);
		
		query.executeUpdate();
	}

}
