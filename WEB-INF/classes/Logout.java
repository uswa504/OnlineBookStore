import java.io.*;
import javax.swing.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class Logout extends HttpServlet {
  protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
    response.setContentType("text/html");
    PrintWriter out=response.getWriter();
    HttpSession session=request.getSession(false);
    session.invalidate();
    out.print("You are successfully logged out!");
    response.sendRedirect("login.html");
    out.close();
    }
}
