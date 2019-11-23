import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
public class View_Orders extends HttpServlet {
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    PrintWriter out = response.getWriter();
    out.println("<html>");
    out.println("<body bgcolor=\"#ffffff\">");
    try{
    Class.forName("com.mysql.jdbc.Driver");
    String url = "jdbc:mysql://127.0.0.1/obs";
    HttpSession session = request.getSession(false);
    out.println("<div style= 'color:beige;font:700 30pt arial;background-color:green;text-align:center'>Online Book Store</div>");
    Connection con=DriverManager.getConnection(url,"root","root");
    Statement st=con.createStatement();
     String query="Select * from orders'";
     ResultSet rs = st.executeQuery( query );
     while(rs.next()){
    	    String name = rs.getString("user_name");
          int orderid = Integer.parseInt(rs.getString("orderid"));
    	    out.println(orderid+" ");
          out.println(name+" ");
          out.println("<br>");

	  }
      out.println("<a href='admin_home.html'> Back </a>");
      out.println("</body></html>");
           st.close();
           con.close();
    }
    catch(Exception e){
      out.println(e);
    }
  }
}
