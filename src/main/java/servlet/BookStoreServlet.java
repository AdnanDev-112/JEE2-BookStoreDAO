package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BookStoreDTO;
import model.Book;
import model.Author;

/**
 * Servlet implementation class BookStoreServlet
 */
@WebServlet("/BookStoreServlet")
public class BookStoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private BookStoreDTO bsDTO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	public BookStoreServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		String param_action = request.getParameter("action");
		String tableStr = new String();

		switch (param_action) {

		case "showAllBooks": {
			List<Book> booklist = bsDTO.allBooks();

			// now including the list inside an DTML table [as a String]
			tableStr += "<table border='1'>";
			tableStr += "<tr><td>ID</td><td>Title</td></tr>";

			for (int i = 0; i < booklist.size(); i++) {
				tableStr += "<tr><td>" + String.valueOf(booklist.get(i).getId()) + "</td>" + "<td>"
						+ booklist.get(i).getTitle() + "</td></tr>";
			}
			tableStr += "</table>";
			break;
		}
		case "insertBook": {

			String bookName = request.getParameter("bookName");
			bsDTO.insertBook(bookName);
			tableStr += "<br/><strong>" + bookName + " Book Inserted </strong>";
			break;

		}
		case "showAllAuthors": {
			List<Author> authorList = bsDTO.allAuthors();

			// now including the list inside an DTML table [as a String]
			tableStr += "<table border='1'>";
			tableStr += "<tr><td>ID</td><td>Author</td><td>Country</td></tr>";

			for (int i = 0; i < authorList.size(); i++) {
				tableStr += "<tr><td>" + String.valueOf(authorList.get(i).getId()) + "</td>" + "<td>"
						+ authorList.get(i).getName() + "</td>" + "<td>" + authorList.get(i).getCountry()
						+ "</td></tr>";
			}
			tableStr += "</table>";
			break;
		}

		case "insertAuthor": {
			String authorName1 = request.getParameter("authorName");
			String countryName = request.getParameter("countryName");

			bsDTO.insertAuthor(authorName1, countryName);
			tableStr += "<br/><strong>" + authorName1 + " Author Inserted </strong>";
			break;
		}
		case "updateBookSimple": {
			try {
				String idString = request.getParameter("bookID");
				int bookID = Integer.parseInt(idString);
				String bookName = request.getParameter("bookName");

				bsDTO.updateABookSimple(bookID, bookName);
				tableStr += "<br/> <strong> Book Updated </strong>";
				break;

			} catch (Exception e) {
				tableStr += "Error Occured" + e;
				break;
			}

		}
		case "authorList": {
			List<Author> authorList = bsDTO.allAuthors();

			HttpSession session = request.getSession();
			session.setAttribute("authorList", authorList);

			RequestDispatcher dispatcher = request.getRequestDispatcher("ViewAuthorBooks.jsp");
			dispatcher.forward(request, response);
			break;

		}

//		Advanced Functions
		case "bookByAuthor": {
			String idString = request.getParameter("authorID");

			try {
				int authorID = Integer.parseInt(idString);

				Author author = bsDTO.getBooksByAuthorIDUsingNamedQuery(authorID);

				tableStr += "Author: " + author.getName() + " Lives in: " + author.getCountry()
						+ " Wrote following Books ";
				tableStr += "<br/>";

				tableStr += "<table border='1'>";
				tableStr += "<tr><td>ID</td><td>Title</td></tr>";

				for (int i = 0; i < author.getBooks().size(); i++) {
					tableStr += "<tr><td>" + String.valueOf(author.getBooks().get(i).getId()) + "</td>" + "<td>"

							+ author.getBooks().get(i).getTitle() + "</td></tr>";
				}
				tableStr += "</table>";

			} catch (Exception e) {
				tableStr += e.toString();

			}
			break;
		}

		case "bookList": {
			List<Book> bookList = bsDTO.allBooks();

			HttpSession session = request.getSession();
			session.setAttribute("bookList", bookList);

			RequestDispatcher dispatcher = request.getRequestDispatcher("BookList.jsp");
			dispatcher.forward(request, response);
			break;

		}

		case "bookInfo": {

			try {
				String idString = request.getParameter("bookID");
				
				int bookID = Integer.parseInt(idString);
				
				Book bookInfo = bsDTO.getBookInfo(bookID);
				
				HttpSession session = request.getSession();
				session.setAttribute("bookInfo", bookInfo);

				RequestDispatcher dispatcher = request.getRequestDispatcher("BookDetails.jsp");
				dispatcher.forward(request, response);
			} catch (Exception e) {
				tableStr += e.toString();

			}

			break;
			}
			
		case "insertBookAuthorList": {
			List<Author> authorList = bsDTO.allAuthors();

			HttpSession session = request.getSession();
			session.setAttribute("authorList", authorList);

			RequestDispatcher dispatcher = request.getRequestDispatcher("InsertBookWithDetails.jsp");
			dispatcher.forward(request, response);
			break;

		}
		case "insertBookWithDetails": {
			 
			String name = request.getParameter("bookName");
			String cbxAuthor = request.getParameter("authorID");
			int authorID = Integer.parseInt(cbxAuthor);
			String textSummary = request.getParameter("textSummary");
			
			bsDTO.insertBookWithDetails(authorID, name, textSummary);
			
			tableStr += "<br/><strong>Book Inserted Successfully</strong>";
			break;
		
		}
		
		case "bookListComment": {
			List<Book> bookList = bsDTO.allBooks();

			HttpSession session = request.getSession();
			session.setAttribute("booklist", bookList);

			RequestDispatcher dispatcher = request.getRequestDispatcher("AddBookComment.jsp");
			dispatcher.forward(request, response);
			break;

		}
		
		case "insertCommentToBook": {
			
			String cbxBook = request.getParameter("bookID");
			int bookID = Integer.parseInt(cbxBook);
			String textComment = request.getParameter("textComment");
			
			bsDTO.insertCommentToBook(bookID, textComment);
			
			tableStr += "<br/><strong>Comment Inserted Successfully</strong>";
			break;
		
		}
		
		case "assignExistingBooktoAuthor":{
			List<Book> bookList = bsDTO.allBooks();
			
			List<Author> authorList = bsDTO.allAuthors();
			
			HttpSession session = request.getSession();
			session.setAttribute("bookList", bookList);
			session.setAttribute("authorList", authorList);

			RequestDispatcher dispatcher = request.getRequestDispatcher("AssignExisting.jsp");
			dispatcher.forward(request, response);
			break;
		}
		
		case "assignBookAuthor": {
			
			String cbxBook = request.getParameter("bookID");
			int bookID = Integer.parseInt(cbxBook);
			String cbxAuthor = request.getParameter("authorID");
			int authorID = Integer.parseInt(cbxAuthor);
			
			
			bsDTO.assignBookToAuthor(bookID, authorID);
			
			tableStr += "<br/><strong>Author Assigned Book Successfully</strong>";
			break;
		}
		
		

		

		}
		;// Exit Switch

		response.setContentType("text/html;charsetUTF-8");

		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Book Store App</title>");
		out.println("<head>");

		out.println("Displayed @ " + new java.util.Date() + "<br/>" + tableStr);
		out.println("<br/> <a href='/BookStoreDAO'>Home</a>");
		out.println("<body>");
		out.println("</body>");
		out.println("</html>");
		out.close();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
