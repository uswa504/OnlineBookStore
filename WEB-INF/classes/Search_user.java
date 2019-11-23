import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
public class Search_user extends HttpServlet {
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession(false);
    PrintWriter out = response.getWriter();
    String username=request.getParameter("uname");
    out.println("<html>");
    out.println("<body bgcolor=\"#ffffff\">");
    try{
    Class.forName("com.mysql.jdbc.Driver");
    String url = "jdbc:mysql://127.0.0.1/obs";
    Connection con=DriverManager.getConnection(url,"root","root");
    Statement st=con.createStatement();
     String query="Select * from user where user_name ='"+username+"'";
     ResultSet rs = st.executeQuery( query );
     while(rs.next()){
    	    String name = rs.getString("user_name");
    	    String email = rs.getString("user_email");
          String phone = rs.getString("user_phone");
          String address = rs.getString("user_address");
          String city = rs.getString("user_city");
          String type = rs.getString("type");
          if ("0".equals(type)) {
            type = "admin";
          }
          else if ("1".equals(type)) {
            type = "customer";
          }
          out.println("<table border = '1'>");
          out.println("<tr>");
    	    out.println("<td>"+name+"</td>");
    	    out.println("<td>"+email+"</td>");
    	    out.println("<td>"+phone+"</td>");
    	    out.println("<td>"+address+"</td>");
          out.println("<td>"+city+"</td>");
          out.println("<td>"+type+"</td>");
          out.println("</table>");
	  }
out.println("</body></html>");
           st.close();
           con.close();
    }
    catch(Exception e){
      out.println(e);
    }
  }
}
