import java.io.*;
import java.util.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class Add_to_cart extends HttpServlet{
public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
  PrintWriter out = response.getWriter();
  try{
    response.setContentType("text/html");
    String id=request.getParameter("id");
    HttpSession session = request.getSession(false);
      Class.forName("com.mysql.jdbc.Driver");
      String url = "jdbc:mysql://127.0.0.1/obs";
      Connection con=DriverManager.getConnection(url, "root", "root");
      Statement st=con.createStatement();
      String query = "select * from book where bookid = " + id;
      ResultSet rs=st.executeQuery(query);
      if(rs.next()){
        String name = rs.getString("book_name");
        String author = rs.getString("book_author");
        String genre = rs.getString("book_genre");
        int price = Integer.parseInt(rs.getString("book_price"));
        int bookid= Integer.parseInt(rs.getString("bookid"));
        Book book = new Book(bookid, name, author, genre, price);
        if (session.getAttribute("cart") == null) {
          List<Book> cart = new ArrayList<>();
          cart.add(book);
  			  session.setAttribute("cart", cart);
  		  }
        else {
          @SuppressWarnings("unchecked")
			    List<Book> cart = (ArrayList<Book>)session.getAttribute("cart");
				  cart.add(book);
          session.setAttribute("cart", cart);
			  }
    }
      response.sendRedirect("Browse_Books");
      //out.println("Thank you for signing up. Please log_in to continue");
    }
    catch(Exception e){
      out.println(e);
    }
    out.close();
  }
}
