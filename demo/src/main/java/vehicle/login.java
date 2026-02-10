package vehicle;
 import java.io.IOException;
 import java.sql.*;
 import javax.servlet.ServletException;
 import javax.servlet.annotation.WebServlet;
 import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class login extends HttpServlet
 {
    private  static final long serialVersionUID = 1L;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
            

        try {
            Conn c = new Conn();

            String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
            PreparedStatement ps = c.con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
               HttpSession session = request.getSession();
               session.setAttribute("user", email);
                response.sendRedirect("dashboard.html");
            } else {
                
                response.sendRedirect("login.html?error=Invalid email or password");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }

}
