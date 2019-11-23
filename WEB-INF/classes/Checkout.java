import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import javax.swing.*;
public class Checkout extends HttpServlet {
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	response.setContentType("text/html");
	PrintWriter out = response.getWriter();
  out.println("<html>");
  out.println("<body>");
    HttpSession session = request.getSession(false);
    String name=(String)session.getAttribute("name");
    try{
    Class.forName("com.mysql.jdbc.Driver");
    String url = "jdbc:mysql://127.0.0.1/obs";
    Connection con=DriverManager.getConnection(url, "root", "root");
    PreparedStatement preparedStatement = null;
    Statement st=con.createStatement();
    String query = "insert into orders(user_name) values (?)"; //Insert user details into the table 'USERS'
    preparedStatement = con.prepareStatement(query); //Making use of prepared statements here to insert bunch of data
    preparedStatement.setString(1, name);
    int i= preparedStatement.executeUpdate();
    if (i!=0){
      response.sendRedirect("checkout.html");
    }
     st.close();
     con.close();
    }
    catch(Exception e){
      out.println(e);
    }
  }
}
