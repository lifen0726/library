package com.library.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Book")
public class Book {

    @Id
    private String ISBN;
    private String name;
    private String author;
    private String introduction;
    
    // Getters and Setters
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	@Override
	public String toString() {
		return "Book [ISBN=" + ISBN + ", name=" + name + ", author=" + author + ", introduction=" + introduction + "]";
	}
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
    
}

