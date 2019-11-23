import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import javax.swing.*;
public class Insert extends HttpServlet {
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	response.setContentType("text/html");
	PrintWriter out = response.getWriter();
  out.println("<html>");
  out.println("<body>");
    String email = request.getParameter("email");
    String name=request.getParameter("uname");
    String password = request.getParameter("password");
    String user_type = request.getParameter("user_type");
    String address=request.getParameter("address");
    String city = request.getParameter("city");
    String phone = request.getParameter("phone");
    if ("admin".equals(user_type)) {
      user_type = "0";
    }
    else if ("customer".equals(user_type)) {
      user_type = "1";
    }
    try{
    Class.forName("com.mysql.jdbc.Driver");
    String url = "jdbc:mysql://127.0.0.1/obs";
    Connection con=DriverManager.getConnection(url, "root", "root");
    PreparedStatement preparedStatement = null;
    Statement st=con.createStatement();
    String query = "insert into user(user_email, user_name, user_passwd, user_phone, user_address, user_city, type) values (?,?,?,?,?,?,?)"; //Insert user details into the table 'USERS'
    preparedStatement = con.prepareStatement(query); //Making use of prepared statements here to insert bunch of data
    preparedStatement.setString(1, email);
    preparedStatement.setString(2, name);
    preparedStatement.setString(3, password);
    preparedStatement.setString(4, phone);
    preparedStatement.setString(5, address);
    preparedStatement.setString(6, city);
    preparedStatement.setString(7, user_type);
    int i= preparedStatement.executeUpdate();
    if (i!=0){
      response.sendRedirect("login.html");
      out.println("Thank you for signing up. Please log_in to continue");
    }
     st.close();
     con.close();
    }
    catch(Exception e){
      out.println(e);
    }
  }
}
