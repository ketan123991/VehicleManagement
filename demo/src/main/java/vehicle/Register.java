package vehicle;
import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/register")
public class Register extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String mobile = request.getParameter("mobile");
        String password = request.getParameter("password");

        try {
            
            String confirmPassword = request.getParameter("confirm_password");

                 if (!password.equals(confirmPassword)) {
            response.getWriter().println("Passwords do not match!");
                        return; 
            }

       Conn c = new Conn();

String sql = "INSERT INTO users (first_name, last_name, email, address, mobile, password) VALUES (?, ?, ?, ?, ?, ?)";

PreparedStatement ps = c.con.prepareStatement(sql);

ps.setString(1, firstName);
ps.setString(2, lastName);
ps.setString(3, email);
ps.setString(4, address);
ps.setString(5, mobile);
ps.setString(6, password);

int rows = ps.executeUpdate();

if (rows > 0) {
    response.getWriter().println("Registration Successful!");
} else {
    response.getWriter().println("Registration Failed!");
}
          
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }



}
