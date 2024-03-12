package dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import model.Book;
import model.Bookcomment;
import model.Booksummary;
import model.Author;

/**
 * Session Bean implementation class BookStoreDTO
 */
@Stateless
@LocalBean
public class BookStoreDTO {

	/**
	 * Default constructor.
	 */
	public BookStoreDTO() {
		// TODO Auto-generated constructor stub
	}

	@PersistenceContext(unitName = "BookStoreDAO")
	EntityManager entityManager;

	public List<Book> allBooks() {
		List queryResults = entityManager.createQuery("SELECT b from Book b").getResultList();
		List<Book> listResult = new ArrayList<Book>();

		for (int i = 0; i < queryResults.size(); i++) {
			Book b = new Book();
			b = (Book) queryResults.get(i);
			listResult.add(b);
		}
		;

		return listResult;
	};

	public void insertBook(String name) {
		Book b = new Book();
		b.setTitle(name);
		entityManager.persist(b);
	};

	public List<Author> allAuthors() {
		List queryResults = entityManager.createNamedQuery("Author.findAll", Author.class).getResultList();
		List<Author> listResult = new ArrayList<Author>();

		for (int i = 0; i < queryResults.size(); i++) {
			Author a = new Author();
			a = (Author) queryResults.get(i);
			listResult.add(a);
		}
		;

		return listResult;
	};

	public void insertAuthor(String name, String country) {
		Author a = new Author();
		a.setName(name);
		a.setCountry(country);
		entityManager.persist(a);
	};

	public void updateABookSimple(int bookId, String bookName) {
		Book a = entityManager.find(Book.class, bookId);
		a.setTitle(bookName);
		entityManager.persist(a);
	}

//    	Advanced methods
	public Author getBooksByAuthorIDUsingNamedQuery(int authorID) {
		Author queryResult = entityManager.createNamedQuery("Author.findBooksByAuthorID", Author.class)
				.setParameter("id", authorID).getSingleResult();

		return queryResult;
	};

	
	public Book getBookInfo(int id) {
	    Book bResult = entityManager.find(Book.class, id);
	    
	    // If the book exists
	    if (bResult != null) {
	        // Query and set book summaries
	        try {
	            bResult.setBooksummaries(entityManager.createQuery("SELECT bs FROM Booksummary bs WHERE bs.book.id = :id", Booksummary.class)
	                                                   .setParameter("id", id)
	                                                   .getResultList());
	        } catch (NoResultException e) {
	            bResult.setBooksummaries(new ArrayList<>());
	        }
	        
	        // Query and set book comments
	        try {
	            bResult.setBookcomments(entityManager.createQuery("SELECT bc FROM Bookcomment bc WHERE bc.book.id = :id", Bookcomment.class)
	                                                  .setParameter("id", id)
	                                                  .getResultList());
	        } catch (NoResultException e) {
	            bResult.setBookcomments(new ArrayList<>());
	        }
	        
	        // Query and set authors
	        try {
	            bResult.setAuthors(entityManager.createQuery("SELECT a FROM Author a JOIN a.books b WHERE b.id = :id", Author.class)
	                                             .setParameter("id", id)
	                                             .getResultList());
	        } catch (NoResultException e) {
	            bResult.setAuthors(new ArrayList<>());
	        }
	    }
	    
	    return bResult;
	}
	
	public void insertBookWithDetails(int authorID,String name, String summary) {
		Author a = entityManager.find(Author.class, authorID);
		
		try {
		Book b = new Book();
		b.setTitle(name);
		
		Booksummary bS = new Booksummary();
		
		bS.setSummary(summary);
		bS.setBook(b);
		
		b.addBooksummary(bS);
		b.getAuthors().add(a);
		
		entityManager.persist(b);
		entityManager.persist(bS);
		}catch(Exception e) {
			System.out.println(e);
		}

		
		

		
	};
	public void insertCommentToBook(int bookID, String comment) {
		Book b = entityManager.find(Book.class, bookID);
		
		try {
		
		Bookcomment bC = new Bookcomment();
		
		bC.setComment(comment);
		bC.setBook(b);
		
		b.addBookcomment(bC);
		
		entityManager.persist(b);
		entityManager.persist(bC);
		}catch(Exception e) {
			System.out.println(e);
		}
		
	};
	
	public void assignBookToAuthor(int bookID, int authorID) {
		Book b = entityManager.find(Book.class, bookID);
		Author a = entityManager.find(Author.class, authorID);
		
		try {
//			List<Book> bookList = a.getBooks();
//			List<Author> bookAuthors = b.getAuthors();
//			bookList.add(b);
//			bookAuthors.add(a);
			
			b.getAuthors().add(a);
		
//			a.setBooks(bookList);
//			b.setAuthors(bookAuthors);
//		entityManager.persist(a);
		entityManager.persist(b);
		
		}catch(Exception e) {
			System.out.println(e);
		}
	};


};
