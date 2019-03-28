package com.mikeschulenbergdev.bookwyrm.test;

import java.util.List;

import com.mikeschulenbergdev.bookwyrm.entity.Author;
import com.mikeschulenbergdev.bookwyrm.entity.Book;

public class BookwyrmTest {

	public static void main(String[] args) {
		Book book1 = new Book("Cool Book", "Fantasy", null, null);
		Book book2 = new Book("Awesome Book", "Fantasy", "Pandamazing Adventures", 3);
		
		Author author1 = new Author("Robert", "E.", "Howard");
		Author author2 = new Author("Dirk", null, "Steele");
		Author author3 = new Author("Robin", null, "Hobb");
		Author author4 = new Author("Jim", null, "Butcher");
		
		book1.addAuthor(author1);
		book1.addAuthor(author2);	
		book1.addAuthor(author3);	
		book1.addAuthor(author4);
		
		book2.addAuthor(author1);
		book2.addAuthor(author2);	
		book2.addAuthor(author3);	
		book2.addAuthor(author4);	
		
		book1.removeAuthor(author2);
		
		for (Author author : book1.getAuthor()) {
			System.out.println(author + " " + author.getBooks());
		}
		
		System.out.println();
		
		book2.removeAuthor(author4);
		
		for (Author author : book2.getAuthor()) {
			System.out.println(author + " " + author.getBooks());
		}
	}

}
