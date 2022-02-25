package com.example.myfirstprojectspring;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class BookService {
		
	static List<Book>  books = new ArrayList<Book>(); 
	static {
		//String title, String author, int pages, int year, String iSBN
		Book book1 = new Book ("Anna Karenina", "Tolstoi", 562, 1896, "BR5GV-5-ERG5-6567", new Date(1, 15, 2022));
		Book book2 = new Book ("To the lighthouse", "V Wolf", 235, 1626, "56756-DFGRT-554", new Date(1, 15, 2022));
		Book book3 = new Book ("I LOVE JAVA", "Anna", 2, 2023, "GWR-456", new Date());
		//Book book4 = new Book ();
		//Book book5 = new Book ();
		//Book book6 = new Book ();
		books.add(book3);
		books.add(book2);
		books.add(book1);
	}
	
	public List<Book> queryBooksFromArray() {
		
		System.out.println ("Books" + books);
		
		return books;
	}
	
	public Book addBookToArray(Book book) {
		
		books.add(book);
		
		return book;
	}
	
	public String deleteBookFromArray(String title ) {
		
		int index = findBookByTilte(title);
		books.remove(index);
		
		return "Book deleted by title";
	}
	
	public String deleteBookFromArray(int index ) {
		
		books.remove(index);
		
		return "Book deleted by index";
	}

	public int findBookByTilte(String title) {
		
		int index = -1;
		for ( Book bookTemporal  : books) {
			
			if ( bookTemporal.getTitle().equals(title) ) {	
				index = books.indexOf(bookTemporal);
			}
		}
		return index;
	}

	public Book replaceBook(int indexBook, Book book) {
		
		books.set(indexBook, book);
		
		
		return book;
		
	}
		
	public Book getBookByIndex (int index) {
		
		Book book = books.get(index);
		
		return book;
	}
	
}
