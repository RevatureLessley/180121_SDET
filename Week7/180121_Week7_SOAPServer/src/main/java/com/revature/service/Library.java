package com.revature.service;

import java.util.List;

//jws = JAX_WS, this is library for the API for interacting with SOAP Web services
import javax.jws.WebService;

import com.revature.bean.Book;
import com.revature.exception.LibraryYearException;

//Denotes a SOAP webservice
@WebService
public interface Library {
	List<Book> getAllBooks();
	String addBook(Book book) throws LibraryYearException;
}
