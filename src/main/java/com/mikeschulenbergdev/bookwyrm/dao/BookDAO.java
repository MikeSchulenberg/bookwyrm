package com.mikeschulenbergdev.bookwyrm.dao;

import java.util.List;

import com.mikeschulenbergdev.bookwyrm.entity.Book;

public interface BookDAO {

	public List<Book> findAll();
	
	public Book findByID(int id);
	
	public void save(Book book);
	
	public void deleteByID(int id);
	
}
