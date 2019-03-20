package com.san.spring.dao;

import java.util.List;

import com.san.spring.model.Book;

public interface BookDAO {

	// Save record	
	long save(Book book);
	
	//Get a single record
	Book get(long id);
	
	//Get all the records
	List<Book> list();
	
	// Update the record	
	void update(long id, Book book);
	
	// delete a record
	void delete(long id);
	
		
	
}
