package vehicle;
import java.sql.*;

public class Conn {
    Connection con;
    Statement s;
      Conn(){
           try{
               Class.forName("com.mysql.cj.jdbc.Driver");
               con=DriverManager.getConnection("jdbc:mysql:/vehiclesystem","root","kr58@kdr");
               // s=con.createStatement();
        
             }
            catch(Exception e){
               e.printStackTrace();
            }
         }
}
