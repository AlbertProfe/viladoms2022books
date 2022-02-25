package com.example.myfirstprojectspring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mylibrary")
public class WebController {
	
	@Autowired
	BookService bookService;

	
	
	  @RequestMapping("/home")
	  public String getWeb (Model containerToView) {
	  
		  containerToView.addAttribute("booksfromController",
				 bookService.queryBooksFromArray());
	  
		  System.out.println("Controller Web request ...");
	  
	  return "web"; }
	 
	
	
	/*
	 * @RequestMapping("/home") public String getWeb () {
	 * 
	 * System.out.println("Controller Web request ...");
	 * 
	 * return "web"; }
	 */
	 

}
