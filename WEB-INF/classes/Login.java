import java.io.*;
import javax.swing.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class Login extends HttpServlet {
public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    String n=request.getParameter("uname");
    String p=request.getParameter("passwd");
    String user_type = request.getParameter("user_type");
    if ("admin".equals(user_type)) {
      user_type = "0";
    }
    else if ("customer".equals(user_type)) {
      user_type = "1";
    }
    if(Login_Backend.validate(n, p, user_type)){
      HttpSession session = request.getSession(true);
      session.setAttribute("name", n);
      session.setAttribute("passwd", p);
      if(user_type.equals("1")){
        session.setAttribute("loggedIn", "customer");
        RequestDispatcher rd=request.getRequestDispatcher("/user_home.html");
        rd.forward(request,response);
      }
      else{
        session.setAttribute("loggedIn", "admin");
        RequestDispatcher rd=request.getRequestDispatcher("/admin_home.html");
        rd.include(request, response);
      }
    }
    else{
      RequestDispatcher rd=request.getRequestDispatcher("/login.html");
      rd.include(request,response);
      out.println("Username or Password is incorrect");
        }
    out.close();
    }
}
