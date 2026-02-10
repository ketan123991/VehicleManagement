package vehicle;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("myBookings")
public class MyBooking extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        StringBuilder json = new StringBuilder();
        json.append("[");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/vehicle_db",
                "root",
                "password"
            );

            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM service_booking"
            );

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                json.append("{");
                json.append("\"booking_id\":").append(rs.getInt("booking_id")).append(",");
                json.append("\"customer_name\":\"").append(rs.getString("customer_name")).append("\",");
                json.append("\"vehicle_no\":\"").append(rs.getString("vehicle_no")).append("\",");
                json.append("\"service_type\":\"").append(rs.getString("service_type")).append("\",");
                json.append("\"service_date\":\"").append(rs.getDate("service_date")).append("\",");
                json.append("\"status\":\"").append(rs.getString("status")).append("\"");
                json.append("},");
            }

            if (json.charAt(json.length() - 1) == ',') {
                json.deleteCharAt(json.length() - 1);
            }

            json.append("]");
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        out.print(json.toString());
        out.flush();
    }
}
