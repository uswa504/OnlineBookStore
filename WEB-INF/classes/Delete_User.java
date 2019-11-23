import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import javax.swing.*;
public class Delete_User extends HttpServlet {
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    PrintWriter out = response.getWriter();
    String username=request.getParameter("uname");
    try{
    Class.forName("com.mysql.jdbc.Driver");
    String url = "jdbc:mysql://127.0.0.1/obs";
    Connection con=DriverManager.getConnection(url,"root","root");
    Statement st=con.createStatement();
     String query="Delete from user where user_name ='"+username+"'";
     int rs = st.executeUpdate( query );
     if(rs != 0){
       response.sendRedirect("delete_user.html");
        out.println("Book deleted");
	   }
     else{
       out.println("Book not found");
     }
           st.close();
           con.close();
    }
    catch(Exception e){
      out.println(e);
    }
  }
}
