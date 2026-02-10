package vehicle;

import java.io.IOException;
import java.sql.PreparedStatement;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/addVehicle")
public class AddVehicleServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String vehicleNumber = request.getParameter("vehicleNumber");
        String vehicleType = request.getParameter("vehicleType");
        String brand = request.getParameter("brand");
        String model = request.getParameter("model");
        int year = Integer.parseInt(request.getParameter("year"));

        try {
            Conn c = new Conn();
            String sql = "INSERT INTO addvehicles(vehicle_number, vehicle_type, brand, model, year) VALUES (?,?,?,?,?)";
            PreparedStatement ps = c.con.prepareStatement(sql);

            ps.setString(1, vehicleNumber);
            ps.setString(2, vehicleType);
            ps.setString(3, brand);
            ps.setString(4, model);
            ps.setInt(5, year);

            ps.executeUpdate();
            response.getWriter().println("Vehicle Added Successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
