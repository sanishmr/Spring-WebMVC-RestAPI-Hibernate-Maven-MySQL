package com.san.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
