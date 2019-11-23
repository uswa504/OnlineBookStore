import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
public class Search_by_Author extends HttpServlet {
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    PrintWriter out = response.getWriter();
    String bookauthor=request.getParameter("bauthor");
    out.println("<html>");
    out.println("<body bgcolor=\"#ffffff\">");
    try{
    Class.forName("com.mysql.jdbc.Driver");
    String url = "jdbc:mysql://127.0.0.1/obs";
    HttpSession session = request.getSession(false);
    if(session.getAttribute("loggedIn") == "admin"){
      out.println("<div style= 'color:beige;font:700 30pt arial;background-color:green;text-align:center'>Online Book Store</div>");
    }
    else {
      out.println("<div style= 'color:beige;font:700 30pt arial;background-color:green;text-align:center'>Online Book Store");
      out.println("</div>");
      out.println("<a href='View_cart'> View Cart </a>");
      out.println(" ");
      out.println("<a href='Checkout'> Checkout </a>");
    }
    Connection con=DriverManager.getConnection(url,"root","root");
    Statement st=con.createStatement();
     String query="Select * from book where book_author ='"+bookauthor+"'";
     ResultSet rs = st.executeQuery( query );
     while(rs.next()){
    	    String name = rs.getString("book_name");
    	    String author = rs.getString("book_author");
          String genre = rs.getString("book_genre");
          int price = Integer.parseInt(rs.getString("book_price"));
          out.println("<table border = '1'>");
          out.println("<tr>");
    	    out.println("<td>"+name+"</td>");
    	    out.println("<td>"+author+"</td>");
    	    out.println("<td>"+genre+"</td>");
    	    out.println("<td>"+price+"</td>");
          if(session.getAttribute("loggedIn") == "customer"){
            String id=request.getParameter("id");
            out.println("<td><a href='Add_to_cart?id=" + id + "'> Add to Cart </a></td>");
            out.println("</tr>");
            out.println("</table>");
          }
          else{
            out.println("</tr>");
            out.println("</table>");
          }

	  }
    if(session.getAttribute("loggedIn") == "customer"){
      out.println("<a href='user_home.html'> Back </a>");
    }
    else{
      out.println("<a href='admin_home.html'> Back </a>");
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
