package com.example.myfirstprojectspring;

import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

//POJO this class is used to create objects which i use to populate our array
public class Book {
	@NotBlank
	private String title;
	@Size(min=5,  max = 20)
	private String author;
	private int pages;
	@Min(value = 1000, message = "min year is in this case 1000")
	private int year;
	@NotNull
	private String ISBN;
	private LocalDate createdat;
	@NotEmpty
	private Date datePublished;
	//@Email
	//private String email
	
	public Book () {}
	
	public Book(String title, String author, int pages, int year, String ISBN, Date datePublished ) {
		super();
		this.title = title;
		this.author = author;
		this.pages = pages;
		this.year = year;
		this.ISBN = ISBN;
		this.datePublished = datePublished;
	
	}
	
	
	public Book(String title, String author, int pages, int year, String ISBN, Date datePublished , LocalDate createdat) {
		super();
		this.title = title;
		this.author = author;
		this.pages = pages;
		this.year = year;
		this.ISBN = ISBN;
		this.datePublished = datePublished;
		this.createdat = createdat;	 	
		
	}
	
	
	public Date getDatePublished() {
		return datePublished;
	}
	public void setDatePublished(Date datePublished) {
		this.datePublished = datePublished;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	@Override
	public String toString() {
		return "Book [title=" + title + ", author=" + author + ", pages=" + pages + ", year=" + year + ", ISBN=" + ISBN
				+ ", createdat=" + createdat + "]";
	}
	public LocalDate getCreatedat() {
		return createdat;
	}
	public void setCreatedat(LocalDate createdat) {
		this.createdat = createdat;
	}

	
}
