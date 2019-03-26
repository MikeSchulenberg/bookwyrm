package com.mikeschulenbergdev.bookwyrm.test;

import java.util.List;

import com.mikeschulenbergdev.bookwyrm.entity.Author;
import com.mikeschulenbergdev.bookwyrm.entity.Book;

public class BookwyrmTest {

	public static void main(String[] args) {
		Book book = new Book();
		Author author1 = new Author("Robert", "E.", "Howard");
		Author author2 = new Author("Dirk", null, "Steele");
		
		book.addAuthor(author1);
		book.addAuthor(author2);	
		
		List<Author> authors = book.getAuthor();
		
		for (Author author : authors) {
			System.out.println(author);
		}
	}

}
