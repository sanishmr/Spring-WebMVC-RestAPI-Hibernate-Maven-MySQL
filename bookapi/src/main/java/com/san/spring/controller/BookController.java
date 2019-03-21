package com.san.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.MediaType;

import com.san.spring.model.Book;
import com.san.spring.service.BookService;

@RestController
@RequestMapping
public class BookController {
	
	@Autowired
	private BookService bookService;

	
	@GetMapping(value="/", produces = MediaType.TEXT_PLAIN_VALUE)
    public String index() {

        return "This is Home page";
    }

	
	// Get All the books
	@GetMapping(value="/api/book")   
	public ResponseEntity<List<Book>> list() {
		
		List<Book> list = bookService.list();
		return ResponseEntity.ok().body(list);	
		
	}
	
	// Save the book  -- need to add  @ResponseBody -- HTTP Status 405 - Request method 'POST' not supported (Spring MVC)
	
	@PostMapping("/api/book")
	public  @ResponseBody  ResponseEntity<?> save(@RequestBody Book book) {
		
		long id = bookService.save(book);
		return ResponseEntity.ok().body("Book created with id: " + id);
				
	}
	
	// Get a single book
	
	@GetMapping("/api/book/{id}")
	public ResponseEntity<Book> get(@PathVariable("id") long id) {
		Book book = bookService.get(id);
		return ResponseEntity.ok().body(book);
		
	}
	
	
	// Update the record  --  add  @ResponseBody to avoid org.springframework.web.HttpRequestMethodNotSupportedException: Request method 'PUT' not supported]
	
	@PutMapping("/api/book/{id}")
	public  @ResponseBody ResponseEntity<?> update (@PathVariable("id")long id, @RequestBody  Book book) {
		bookService.update(id, book);
		return ResponseEntity.ok().body("Book has been updated");
		
	}
	
	// Delete the record --  add  @ResponseBody to avoid [org.springframework.web.HttpRequestMethodNotSupportedException: Request method 'DELETE' not supported]
	
	@DeleteMapping("/api/book/{id}")
	public @ResponseBody ResponseEntity<?> delete(@PathVariable("id")long id) {
		bookService.delete(id);
		return ResponseEntity.ok().body("Book has been deleted" );
	}
	
}
