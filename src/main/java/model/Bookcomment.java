package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the bookcomment database table.
 * 
 */
@Entity
@NamedQuery(name="Bookcomment.findAll", query="SELECT b FROM Bookcomment b")
public class Bookcomment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String comment;

	//bi-directional many-to-one association to Book
	@ManyToOne
	@JoinColumn(name="BookId")
	private Book book;

	public Bookcomment() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Book getBook() {
		return this.book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

}