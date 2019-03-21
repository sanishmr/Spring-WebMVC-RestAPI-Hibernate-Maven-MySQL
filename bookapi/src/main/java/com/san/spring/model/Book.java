package com.san.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity(name = "Book")
public class Book {

	/*
	 * //  --  org.hibernate.exception.GenericJDBCException: could not execute statement] with root cause
	// java.sql.SQLException: Field 'id' doesn't have a default value
	 *  GenerationType.IDENTITY to GenerationType.AUTO fixed
	 * INFO: HHH10001501: Connection obtained from JdbcConnectionAccess [org.hibernate.engine.jdbc.env.internal.JdbcEnvironmentInitiator$ConnectionProviderJdbcConnectionAccess@411ae6a6] for (non-JTA) DDL execution was not in auto-commit mode; the Connection 'local transaction' will be committed and the Connection will be set into auto-commit mode.
Hibernate: create table hibernate_sequence (next_val bigint) engine=InnoDB
Hibernate: insert into hibernate_sequence values ( 1 )


INFO: Server startup in 20535 ms
Hibernate: select next_val as id_val from hibernate_sequence for update
Hibernate: update hibernate_sequence set next_val= ? where next_val=?
Hibernate: insert into Book (author, title, ID) values (?, ?, ?)
Mar 21, 2019 12:02:33 PM org.hibernate.engine.jdbc.spi.SqlExceptionHelper logExceptions


	 * 
	 */
	
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY) 		
	@GeneratedValue(strategy = GenerationType.AUTO) 
	@Column(name ="ID")
	private Long id;
	private String title;
	private String author;
	
//	//  @Id  -- nested exception is org.hibernate.id.IdentifierGenerationException: ids for this class must be manually assigned before calling save(): -
	public Long getId() {
		return id;
		
	}
	public void setId(Long id) {
		this.id = id;
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
	
	
	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + "]";
	}
	
	public Book(String title, String author) {
		
		this.title = title;
		this.author = author;
	}

	public Book() {

	}
	
	
}
