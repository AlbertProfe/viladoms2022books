package com.example.myfirstprojectspring;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationCommnadRunner implements CommandLineRunner {

	protected final Log logger = LogFactory.getLog(getClass());	
	List<Book> books = new ArrayList<Book>();

	@Override
	public void run(String... args) throws Exception {
		
		logger.info("Welcome to the runner from commandLineRunner");
		String response = addBooks();
		logger.info("Creating books ... " + response);
		logger.info("Books: " + books);

	}

	public List<Book> getBooks() {
		return books;
	}
	
	public String addBooks() {
		// String title, String author, int pages, int year, String iSBN
		Book book1 = new Book("Anna Karenina", "Tolstoi", 562, 1896, "BR5GV-5-ERG5-6567", new Date(1, 15, 2022));
		Book book2 = new Book("To the lighthouse", "V Wolf", 235, 1626, "56756-DFGRT-554", new Date(1, 15, 2022));
		Book book3 = new Book("Oralndo", "Virginia Wolf", 203, 2023, "4455645-GWR-456", new Date(1, 15, 2022));
		Book book4 = new Book("In Search of Lost Time", "Marcel Proust", 152, 2023, "4455645-GWR-456", new Date(1, 15, 2022));
		// Book book4 = new Book ();
		// Book book5 = new Book ();
		// Book book6 = new Book ();
		books.add(book3);
		books.add(book2);
		books.add(book1);
		books.add(book4);
		
		return "Books created";
	}

}
