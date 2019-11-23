import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import javax.swing.*;
public class ADD_BOOK extends HttpServlet {
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	response.setContentType("text/html");
	PrintWriter out = response.getWriter();
    String book_name = request.getParameter("bname");
    String book_author=request.getParameter("bauthor");
    String genre = request.getParameter("genre");
    int price = Integer.parseInt(request.getParameter("price"));
    try{
    Class.forName("com.mysql.jdbc.Driver");
    String url = "jdbc:mysql://127.0.0.1/obs";
    Connection con=DriverManager.getConnection(url, "root", "root");
    PreparedStatement preparedStatement = null;
    Statement st=con.createStatement();
    String query = "insert into book(book_name, book_author, book_genre, book_price) values (?,?,?,?)"; //Insert user details into the table 'USERS'
    preparedStatement = con.prepareStatement(query); //Making use of prepared statements here to insert bunch of data
    preparedStatement.setString(1, book_name);
    preparedStatement.setString(2, book_author);
    preparedStatement.setString(3, genre);
    preparedStatement.setInt(4, price);
    int i= preparedStatement.executeUpdate();
    if (i!=0)
      response.sendRedirect("add_book.html");
      out.println("Book Added");
     st.close();
     con.close();
    }
    catch(Exception e){
      out.println(e);
    }
  }
}
