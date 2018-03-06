package com.revature.service;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import com.revature.bean.Book;
import com.revature.exception.LibraryYearException;

@WebService(endpointInterface="com.revature.service.Library")
public class LibraryImpl implements Library{
	public List<Book> getAllBooks(){
		
		System.out.println("=====getAllBooks Invoked=====");
		
		List<Book> bookList = new ArrayList<>();
		bookList.add(new Book("Lord of the Bobberts", "Bobbert", 1990));
		bookList.add(new Book("Bobbert and the Philosophers Stone", "Ryan", 2002));
		bookList.add(new Book("The Hitchhikers Guide to the Gala-Bobbert", "Trump", 2007));
		//NOTE: Typically you should invoke your DAOService for grabbing books in
		//this case, for simplicity, I just create it on the spot.
		
		return bookList;
	}
	/*
	 * Our addBook method has an exception risk. If a year is below
	 * 2000, the exception triggers.
	 */
	public String addBook(Book book) throws LibraryYearException{
		System.out.println("Adding a book: " + book);
		if(book.getYear()<2000){
			throw new LibraryYearException("That book is too gosh darn old...: " + book);
		}
		/*
		 * Typically, in the event of a successful insert, you would call your dao for inserting
		 * a book at this point.
		 */
		return "Successfully added book with title: " + book.getTitle();
	}
}
