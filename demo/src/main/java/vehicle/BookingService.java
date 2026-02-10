package vehicle;

import java.io.IOException;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/BookService")
public class BookingService extends HttpServlet {

    // For testing in browser (prevents 405 while checking)
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.getWriter().println("BookingService servlet is working");
    }

    // Called when form is submitted
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String customerName = request.getParameter("customerName");
        String vehicleNo = request.getParameter("vehicleNo");
        String serviceType = request.getParameter("serviceType");
        String serviceDate = request.getParameter("serviceDate");
        String problem = request.getParameter("problem");

        try {
            Conn c = new Conn();

            String sql =
                "INSERT INTO service_booking (customer_name, vehicle_no, service_type, service_date, problem) VALUES (?,?,?,?,?)";

            PreparedStatement ps = c.con.prepareStatement(sql);
            ps.setString(1, customerName);
            ps.setString(2, vehicleNo);
            ps.setString(3, serviceType);
            ps.setString(4, serviceDate);
            ps.setString(5, problem);

            ps.executeUpdate();
            c.con.close();

      response.getWriter().println("BookingService servlet is working");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
