import java.sql.*;
public class Login_Backend {
public static boolean validate(String name, String pass, String user_type ){
boolean status=false;
try{
  Class.forName("com.mysql.jdbc.Driver");
  String url = "jdbc:mysql://127.0.0.1/obs";
  Connection con=DriverManager.getConnection(url, "root", "root");
  PreparedStatement ps=con.prepareStatement("select * from user where user_name=? and user_passwd=? and type = ?");
  ps.setString(1,name);
  ps.setString(2,pass);
  ps.setString(3,user_type);
  ResultSet rs=ps.executeQuery();
  status=rs.next();
}
catch(Exception e){
  System.out.println(e);
}
return status;
}
}
