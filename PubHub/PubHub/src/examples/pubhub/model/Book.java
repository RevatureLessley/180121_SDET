package examples.pubhub.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="books")
public class Book {
	
	@Id 
	@Column(name = "isbn_13")
	private String isbn13;			// International Standard Book Number, unique
	
	@Column(name="title")
	private String title;
	
	@Column(name = "author")
	private String author;
	
	@Column(name = "publish_date")
	private LocalDate publishDate;	// Date of publish to the website
	
	@Column(name = "price")
	private double price;
	
	@Column(name = "content")
	private byte[] content;

	// Constructor used when no date or price is specified
	public Book(String isbn, String title, String author, byte[] content) {
		this.isbn13 = isbn;
		this.title = title;
		this.author = author;
		this.publishDate = LocalDate.now();
		this.content = content;
	}
	
	// Constructor used when a date is specified
	public Book(String isbn, String title, String author, byte[] content, LocalDate publishDate) {
		this.isbn13 = isbn;
		this.title = title;
		this.author = author;
		this.publishDate = publishDate;
		this.content = content;
	}
	
	//no content specified
	public Book(String isbn, String title, String author, Double price) {
		this.isbn13 = isbn;
		this.title = title;
		this.author = author;
		this.publishDate = LocalDate.now();
		this.content = null;	
		this.price = price;
	}
	
	// Constructor used when no date specified
	public Book(String isbn, String title, String author, Double price, byte[] content) {
		this.isbn13 = isbn;
		this.title = title;
		this.author = author;
		this.price = price;
		this.publishDate = LocalDate.now();
		this.content = content;
	}
	
	// Default constructor
	public Book() {
		this.isbn13 = null;
		this.title = null;
		this.author = null;
		this.publishDate = LocalDate.now();
		this.content = null;
		this.price = new Double(0);
	}
	
	public String getIsbn13() {
		return isbn13;
	}

	public void setIsbn13(String isbn13) {
		this.isbn13 = isbn13;
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

	public LocalDate getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(LocalDate publishDate) {
		this.publishDate = publishDate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}
	
	@Override
	public String toString() {
		return "ISBN: " + this.isbn13 + ", Author: " + this.author + ", Title: " + this.title; 
	}
	
	
}
