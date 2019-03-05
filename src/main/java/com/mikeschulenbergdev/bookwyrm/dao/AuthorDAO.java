package com.mikeschulenbergdev.bookwyrm.dao;

import java.util.List;

import com.mikeschulenbergdev.bookwyrm.entity.Author;

public interface AuthorDAO {

	public List<Author> findAll();
	
	public Author findByID(int id);
	
	public void save(Author author);
	
	public void deleteByID(int id);
	
}
