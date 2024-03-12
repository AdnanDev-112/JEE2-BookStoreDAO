package model;

import java.io.Serializable;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the books database table.
 * 
 */
@Entity
@Table(name="books")
@NamedQuery(name="Book.findAll", query="SELECT b FROM Book b")
public class Book implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String title;

	//bi-directional many-to-one association to Bookcomment
	@OneToMany(mappedBy="book")
	private List<Bookcomment> bookcomments;

	//bi-directional many-to-many association to Author
	@ManyToMany
	@JoinTable(
		name="booksauthors"
		, joinColumns={
			@JoinColumn(name="BookId")
			}
		, inverseJoinColumns={
			@JoinColumn(name="AuthorId")
			}
		)
	private List<Author> authors;

	//bi-directional many-to-one association to Booksummary
	@OneToMany(mappedBy="book")
	private List<Booksummary> booksummaries;

	public Book() {
		this.booksummaries = new ArrayList<Booksummary>();
		this.bookcomments = new ArrayList<Bookcomment>();
		this.authors = new ArrayList<Author>();
		
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Bookcomment> getBookcomments() {
		return this.bookcomments;
	}

	public void setBookcomments(List<Bookcomment> bookcomments) {
		this.bookcomments = bookcomments;
	}

	public Bookcomment addBookcomment(Bookcomment bookcomment) {
		getBookcomments().add(bookcomment);
		bookcomment.setBook(this);

		return bookcomment;
	}

	public Bookcomment removeBookcomment(Bookcomment bookcomment) {
		getBookcomments().remove(bookcomment);
		bookcomment.setBook(null);

		return bookcomment;
	}

	public List<Author> getAuthors() {
		return this.authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	public List<Booksummary> getBooksummaries() {
		return this.booksummaries;
	}

	public void setBooksummaries(List<Booksummary> booksummaries) {
		this.booksummaries = booksummaries;
	}

	public Booksummary addBooksummary(Booksummary booksummary) {
		getBooksummaries().add(booksummary);
		booksummary.setBook(this);

		return booksummary;
	}

	public Booksummary removeBooksummary(Booksummary booksummary) {
		getBooksummaries().remove(booksummary);
		booksummary.setBook(null);

		return booksummary;
	}

}