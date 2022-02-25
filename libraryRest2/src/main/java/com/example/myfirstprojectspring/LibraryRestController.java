package com.example.myfirstprojectspring;

import java.time.LocalDate;
import javax.validation.Valid;
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
	
	//here we are creating our end-point rest API
	@GetMapping("books")
	public ResponseEntity<Iterable<Book>> getAllBooks() {
		
		var headers = new HttpHeaders();
		headers.add("ResponseGet", "getAllBooks executed");
		headers.add("version", "1.0 Api Rest Book Object");
		
		return ResponseEntity.accepted().headers(headers).body(bookservice.queryBooksFromArray());
	}
	
	@PostMapping(path = "/addBook", consumes = "application/json" )
	public ResponseEntity<Book> createBook(@Valid @RequestBody Book book) {

		LocalDate localDate = LocalDate.now();
		book.setCreatedat(localDate);
		
		//System.out.println("This is the object that gets from client/postman in java class book: " +  book);
		
		Book bookSaved = bookservice.addBookToArray(book);
		
		var headers = new HttpHeaders();
		headers.add("ResponseCreate", "createBook executed");
		headers.add("version", "1.0 Api Rest Book Object");
		headers.add("Executed Output", "book created");
		
		return ResponseEntity.accepted().headers(headers).body(bookSaved);
		
	}
	
	@DeleteMapping ("/deleteBook/{title}")
	public ResponseEntity<Book> deleteBook  (@PathVariable String title ) {
		
		String responsedelete = ""; 
		int indexBook = bookservice.findBookByTilte (title);
		Book booktodelete = null;
		
		if ( indexBook != -1 ) 
			{
			//bookservice.deleteBookFromArray(title);
			booktodelete = bookservice.getBookByIndex(indexBook);
			bookservice.deleteBookFromArray(indexBook);
			//System.out.println("Book found in " + indexBook + " and deleted");
			responsedelete = responsedelete + "book: " + title  + " - deleted #succes";
			}
		else 
			{
			System.out.println("Book not found, not deleted");
			responsedelete = responsedelete + "book: " + title  + " - not deleted book not found #fail";
			}
		
		var headers = new HttpHeaders();
		headers.add("ResponseDeleted", "deleteBook executed");
		headers.add("version", "1.0 Api Rest Book Object");
		headers.add("Executed Output", responsedelete);
		
		return ResponseEntity.accepted().headers(headers).body(booktodelete);
	}
	
	@PostMapping("/replaceBook/{title}")
	public ResponseEntity<Book> updateBook (@PathVariable String title, @RequestBody Book bookFromRest ) {
		
		String responseUpdate = "";
		Book bookToUpdate = null;

		int indexBook = bookservice.findBookByTilte (title);
		if ( indexBook == -1 ) 
		{
			responseUpdate = responseUpdate + "book not found";
		} else {
			
			bookToUpdate = bookservice.getBookByIndex(indexBook);
			
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
		
		var headers = new HttpHeaders();
		headers.add("ResponseUpdate", "updateBook executed");
		headers.add("version", "1.0 Api Rest Book Object");
		headers.add("Executed Output", responseUpdate);
		
		return ResponseEntity.accepted().headers(headers).body(bookToUpdate);	
	}
	
 	
}
