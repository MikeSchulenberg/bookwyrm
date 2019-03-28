package com.mikeschulenbergdev.bookwyrm.test;

import java.util.List;

import com.mikeschulenbergdev.bookwyrm.entity.Author;
import com.mikeschulenbergdev.bookwyrm.entity.Book;

public class BookwyrmTest {

	public static void main(String[] args) {
		Book book = new Book();
		Author author1 = new Author("Robert", "E.", "Howard");
		Author author2 = new Author("Dirk", null, "Steele");
		Author author3 = new Author("Robin", null, "Hobb");
		Author author4 = new Author("Jim", null, "Butcher");
		
		book.addAuthor(author1);
		book.addAuthor(author2);	
		book.addAuthor(author3);	
		book.addAuthor(author4);	
		
		List<Author> authors = book.getAuthor();
		
		for (Author author : authors) {
			System.out.println(author);
		}
	}

}