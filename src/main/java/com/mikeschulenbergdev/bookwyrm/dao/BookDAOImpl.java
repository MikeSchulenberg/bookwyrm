package com.mikeschulenbergdev.bookwyrm.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.mikeschulenbergdev.bookwyrm.entity.Book;

public class BookDAOImpl implements BookDAO {

	private EntityManager entityManager;
	
	@Autowired
	public BookDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Book> findAll() {
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query<Book> query = currentSession.createQuery("FROM book", Book.class);
		List<Book> books = query.getResultList();

		return books;
	}

	@Override
	public Book findByID(int id) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		Book book = currentSession.get(Book.class, id);
		
		return book;
	}

	@Override
	public void save(Book book) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		currentSession.saveOrUpdate(book);
	}

	@Override
	public void deleteByID(int id) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query<?> query =
				currentSession.createQuery("DELETE FROM book WHERE id:=bookID");
		query.setParameter("bookID", id);
		
		query.executeUpdate();
	}

}
