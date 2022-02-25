package com.example.myfirstprojectspring;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
	
	@Autowired
	private ApplicationCommnadRunner runner;
	
	public List<Book> queryBooksFromArray() {
		
		//System.out.println ("Books" + books);
		
		return runner.getBooks() ;
	}
	
	public Book addBookToArray(Book book) {
		
		runner.getBooks().add(book);
		
		return book;
	}
	
	public String deleteBookFromArray(String title ) {
		
		int index = findBookByTilte(title);
		runner.getBooks().remove(index);
		
		return "Book deleted by title";
	}
	
	public String deleteBookFromArray(int index ) {
		
		runner.getBooks().remove(index);
		
		return "Book deleted by index";
	}

	public int findBookByTilte(String title) {
		
		int index = -1;
		for ( Book bookTemporal  : runner.getBooks()) {
			
			if ( bookTemporal.getTitle().equals(title) ) {	
				index = runner.getBooks().indexOf(bookTemporal);
			}
		}
		return index;
	}

	public Book replaceBook(int indexBook, Book book) {
		
		runner.getBooks().set(indexBook, book);
		
		
		return book;
		
	}
		
	public Book getBookByIndex (int index) {
		
		Book book = runner.getBooks().get(index);
		
		return book;
	}
	
}
