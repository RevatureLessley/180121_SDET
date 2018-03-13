package com.revature.service;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import com.revature.bean.Book;
import com.revature.exception.LibraryYearException;

/*
 * The WebService endpoint tells apache cxf that this class's methods will be used for SOAP interaction.  Therefore, a
 * WSDL will be generated that serves as the contract for these methods.
 */
@WebService(endpointInterface="com.revature.service.Library")
public final class LibraryImpl implements Library {
	public List<Book> getAllBooks() {
		System.out.println("=====getAllBooks() Invoked=====");
		
		List<Book> bookList = new ArrayList<>();
		bookList.add(new Book("Lord of the Bobberts", "Bobbert", 1990));
		bookList.add(new Book("Bobbert and the philosophers stone", "Ryan", 2002));
		bookList.add(new Book("The Hitchhikers Guide to the Gala-Bobbert", "DogBert", 2030));
		
		return bookList;
	}
	
	/*
	 * Our addBook method has an exception risk.  If the year is below 2000, the exception triggers
	 */
	public String addBook(Book book) throws LibraryYearException{
		System.out.println("Adding a book: " + book);
		
		if(book.getYear() < 2000) {
			throw new LibraryYearException("That book is too old...: " + book);
		}
		return "Successfully added book with title: " + book.getTitle();
	}
}
