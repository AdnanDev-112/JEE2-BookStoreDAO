package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the booksummary database table.
 * 
 */
@Entity
@NamedQuery(name="Booksummary.findAll", query="SELECT b FROM Booksummary b")
public class Booksummary implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String summary;

	//bi-directional many-to-one association to Book
	@ManyToOne
	@JoinColumn(name="BookId")
	private Book book;

	public Booksummary() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSummary() {
		return this.summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Book getBook() {
		return this.book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

}