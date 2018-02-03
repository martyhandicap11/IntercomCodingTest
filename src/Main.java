
import java.sql.*;

public class Main
{
    /**
     * The main method
     * @param args = an array of String values
     */

    public static void main(String[] args) throws SQLException {
        Connection con;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/intercomtest", "root", "cardsWS100");
//intercomtest name, root is username and password
            Statement stmt = con.createStatement();
            stmt.executeUpdate("INSERT INTO CUSTOMER" +
                    "VALUES (12, 'Christina','McCardle', 52.986375, -6.043701)");
            stmt.executeUpdate("INSERT INTO CUSTOMER" +
                    "VALUES (1, 'Alice','Cahill', 51.92893, -10.27699\")");
            stmt.executeUpdate("INSERT INTO CUSTOMER" +
                    "VALUES (2, 'Ian','McCardle', 51.8856167, -10.4240951)");
            stmt.executeUpdate("INSERT INTO CUSTOMER" +
                    "VALUES (3, 'Jack','Enright', 52.3191841, -8.5072391\")");
            stmt.executeUpdate("INSERT INTO CUSTOMER" +
                    "VALUES (28, 'Charlie','Halligan', 53.807778, -7.714444)");
            stmt.executeUpdate("INSERT INTO CUSTOMER" +
                    "VALUES (7, 'Frank','Kehoe', 53.4692815, -9.436036\")");

            ResultSet rs = stmt.executeQuery("select * from Customer");
            while (rs.next())
                System.out.println(rs.getInt
                        (1) + "  " + rs.getString(2) + "  " + rs.getString(3));
            con.close();

            ResultSet resultSet = stmt.executeQuery("select user_id, fname, lname, latitude, longitude,p.distance_unit" +
                    "*DEGREES(ACOS(COS(RADIANS(p.latpoint))\n" +
                    "* COS(RADIANS(z.latitude))\n" +
                    "* COS(RADIANS(p.longpoint) - RADIANS(z.longitude))\n" +
                    "*SIN(RADIANS(p.latpoint)),* SIN(RADIANS(z.latitude))))) {" +
                    " AS distance_in_km\n " +
                    "FROM CustomerAS z\n " +
                    "JOIN(   /* these are the query parameters */\n" +
                    "SELECT 53.339428 AS latpoint, -6.257664 AS longpoint,\n " +
                    "100.0 AS radius, 111.045 AS distance_unit\n " +
                    " )AS p ON 1 = 1\n " +
                    " WHERE z.latitude\n " +
                    "BETWEEN p.latpoint - (p.radius / p.distance_unit)\n " +
                    "AND p.latpoint + (p.radius / p.distance_unit)\n " +
                    "AND z.longitude\n " +
                    "BETWEEN p.longpoint - (p.radius / (p.distance_unit * COS(RADIANS(p.latpoint))))\n " +
                    "AND p.longpoint + (p.radius / (p.distance_unit * COS(RADIANS(p.latpoint))))\n " +
                    "ORDER BY user_id\n " +
                    "ASC\n" +
                    "LIMIT 15 ");

            while (resultSet.next())
                System.out.println(rs.getInt
                        (1) + "  " + rs.getString(2) + "  " + rs.getString(3));
            con.close();


    }catch(Exception e){ System.out.println(e);}

    }//end of main

}//end of Main
