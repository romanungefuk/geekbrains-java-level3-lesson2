package geekbrains.java.level3.lesson2.lesson2;


import java.sql.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws SQLException {
         Connection conn2 = null;
         Statement stmt2 = null;
        PreparedStatement ps2 = null;
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:test.db");
             Statement stmt = conn.createStatement();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO Students (ID, Name, Score, 'Group') VALUES (?,?,?,?)");) {
            Class.forName("org.sqlite.JDBC");

            // conn = DriverManager.getConnection("jdbc:sqlite:test.db");
            //stmt = conn.createStatement();

            stmt.execute("DELETE FROM Students");

            conn.setAutoCommit(false);

            // ps = conn.prepareStatement("INSERT INTO Students (ID, Name, Score, 'Group') VALUES (?,?,?,?)");
            ps.setInt(1, 1);
            ps.setString(2, "John");
            ps.setInt(3, 10);
            ps.setInt(4, 2);
            ps.executeUpdate();

            ps.clearBatch();

            ps.setInt(1, 2);
            ps.setString(2, "Paul");
            ps.setInt(3, 12);
            ps.setInt(4, 2);
            ps.addBatch();

            ps.setInt(1, 3);
            ps.setString(2, "Ringo");
            ps.setInt(3, 6);
            ps.setInt(4, 2);
            ps.addBatch();

            ps.setInt(1, 4);
            ps.setString(2, "Jimmy");
            ps.setInt(3, 11);
            ps.setInt(4, 3);
            ps.addBatch();

            int[] w = ps.executeBatch();
            System.out.println(Arrays.toString(w));
            conn.commit();

            String sqlRead = "SELECT * FROM Students";
            ResultSet rs = stmt.executeQuery(sqlRead);

            while (rs.next()) {
                System.out.println(rs.getString("Name") + " | " + rs.getInt("Score"));
            }

            conn2=conn;
            stmt2=stmt;
            ps2=ps;
            System.out.println(stmt.isClosed());
            System.out.println(ps.isClosed());
            System.out.println(conn.isClosed());

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }//finally {
//            try {
//                stmt.close();
//                ps.close();
//                conn.close();
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//        }

        System.out.println(stmt2.isClosed());
        System.out.println(ps2.isClosed());
        System.out.println(conn2.isClosed());
    }
}





