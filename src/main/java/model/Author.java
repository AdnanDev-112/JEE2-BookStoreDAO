package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the authors database table.
 * 
 */
@Entity
@Table(name="authors")
@NamedQuery(name="Author.findAll", query="SELECT a FROM Author a")
@NamedQuery(name="Author.findBooksByAuthorID", query="SELECT a FROM Author a join fetch a.books where a.id=:id")
public class Author implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String country;

	private String name;

	//bi-directional many-to-many association to Book
	@ManyToMany(mappedBy="authors")
	private List<Book> books;

	public Author() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Book> getBooks() {
		return this.books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

}