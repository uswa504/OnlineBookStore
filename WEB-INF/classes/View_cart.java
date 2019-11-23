import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class View_cart extends HttpServlet {
  public void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
    res.setContentType("text/html");
    PrintWriter out = res.getWriter();
    HttpSession session = req.getSession(false);
    int total = 0;
    @SuppressWarnings("unchecked")
    List<Book> cart = (ArrayList<Book>)session.getAttribute("cart");
    out.println("<HTML><HEAD><TITLE>SessionTracker</TITLE></HEAD>");
    out.println("<BODY>");
    out.println("<div style= 'color:beige;font:700 30pt arial;background-color:green;text-align:center'>Online Book Store</div>");
    out.println("You currently have the following items in your cart:<BR>");
    if (cart == null) {
      out.println("<B>None</B>");
    }
    else {
      out.println("<UL>");
      //@SuppressWarnings("unchecked")
      Iterator<Book> iterator = cart.iterator();
      while (iterator.hasNext()) {
        out.println(iterator.next());
        out.println("<br>");
      }
      out.println("</UL>");
    }
    out.println("<br>");
    out.println("Would you like to<BR>");
    out.println("<a href= 'Browse_Books'> Add More Items </a>");
    out.println("<br>");
    out.println("<a href= 'Checkout'> Checkout </a>");
    out.println("</BODY></HTML>");
  }
}
