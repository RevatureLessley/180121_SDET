package examples.pubhub.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
//This class holds a row from book_tags database

@Entity
@Table(name="book_tags")
public class Tag {
	
	//isbn13 and tag function as composite key in the database
	@Id
	@Column(name = "isbn_13")
	private String isbn13;		
	@Column (name = "tag_name")
	private String tag;				


	public Tag(String isbn, String tag) {
		this.isbn13 = isbn;
		this.tag = tag;
	}
	
	public Tag() {
		this.isbn13 = null;
		this.tag = null;
	}
	
	public String getIsbn13() {
		return isbn13;
	}

	public void setIsbn13(String isbn13) {
		this.isbn13 = isbn13;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
	
}
