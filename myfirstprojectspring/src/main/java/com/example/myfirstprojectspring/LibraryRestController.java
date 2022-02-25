package com.example.myfirstprojectspring;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//this annotation is for tell this class that it is a REST CONTROLLER CLASS
@RestController
@RequestMapping("apilibrary")
public class LibraryRestController {
	
	@Autowired
	BookService bookservice;
	
	@Autowired
	MovieService movieservice;
	
	//here we are creating our end-point rest API
	
	  @GetMapping("books")
	public Iterable<Book> getAllBooks() {
	  
	  System.out.println ("Executing: controller getAllBooks");
	  
	  return bookservice.queryBooksFromArray();
	  
	  }

	@GetMapping("movies")
	public Iterable<Movie> getAllMovies (){
		
		System.out.println ("Executing: controller getAllMovies");
		
		return movieservice.queryMovie();
		
		
	}
	
	@PostMapping(path = "/addBook", consumes = "application/json" )
	public Book createBook(@RequestBody Book book) {
		
		
		LocalDate localDate = LocalDate.now();
		book.setCreatedat(localDate);
		
		System.out.println("This is the object that gets from client/postman in java class book: " +  book);
		
		Book bookSaved = bookservice.addBookToArray(book);
		
		
		return bookSaved;
		
		
	}
	
	@DeleteMapping ("/deleteBook/{title}")
	public String deleteBook  (@PathVariable String title ) {
		
		
		
		String responsedelete = ""; 
		int indexBook = bookservice.findBookByTilte (title);
		
		if ( indexBook != -1 ) 
			{
			//bookservice.deleteBookFromArray(title);
			bookservice.deleteBookFromArray(indexBook);
			System.out.println("Book found in " + indexBook + " and deleted");
			responsedelete = responsedelete + "book: " + title  + " - deleted #succes";
			}
		else 
			{
			System.out.println("Book not found, not deleted");
			responsedelete = responsedelete + "book: " + title  + " - not deleted book not found #fail";
			}
		return responsedelete;
	}
	
	@PostMapping("/replaceBook/{title}")
	public String updateBook (@PathVariable String title, @RequestBody Book bookFromRest ) {
		
		String responseUpdate = ""; 

		int indexBook = bookservice.findBookByTilte (title);
		if ( indexBook == -1 ) 
		{
			responseUpdate = responseUpdate + "book not found";
		} else {
			
			Book bookToUpdate = bookservice.getBookByIndex(indexBook);
			
			//we are going to compare both books: 
			//bookFromRest vs bookToUpdate
			//we need to compare each field of our object
			responseUpdate += "book found";
			boolean updated = false;
			
			if  (bookFromRest.getAuthor() != null) {
				responseUpdate += " - author name value updated: " + bookFromRest.getAuthor() +  "( old value: " + bookToUpdate.getAuthor() + ")" ;
				bookToUpdate.setAuthor(bookFromRest.getAuthor());
				updated = true;
			}
			if  (bookFromRest.getISBN() != null) {
				responseUpdate += " - ISBN value updated: " + bookFromRest.getISBN() +  "( old value: " + bookToUpdate.getISBN() + ")" ;
				bookToUpdate.setISBN(bookFromRest.getISBN());
				updated = true;
			}
			if  (bookFromRest.getPages() != 0) {
				responseUpdate += " - pages int value updated: " + bookFromRest.getPages() +  "( old value: " + bookToUpdate.getPages() + ")" ;
				bookToUpdate.setPages(bookFromRest.getPages());
				updated = true;
			}
			if  (bookFromRest.getYear() != 0) {
				responseUpdate += " - year int value updated: " + bookFromRest.getYear() +  "( old value: " + bookToUpdate.getYear() + ")" ;
				bookToUpdate.setYear(bookFromRest.getYear());
				updated = true;
			}
				
			
			if (!updated) responseUpdate += " - try to update but any field updated - something wrong happened";
			
			else bookservice.replaceBook (indexBook, bookToUpdate);
			
		}
		
		
		return responseUpdate;
		
	
		
		
		
	}
	
 	
}
