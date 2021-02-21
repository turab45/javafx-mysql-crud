package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    private static Connection conn = null;

    public static Connection getConnection(){

        if(conn == null){

            try {

                Class.forName("com.mysql.jdbc.Driver");
                conn = (Connection) DriverManager.getConnection("jdbc:mysql://wgudb.ucertify.com/WJ06I6d", "U06I6d", "53688770961");
            } catch (Exception e) {
                System.out.println("ERROR: "+e.getMessage());
                e.printStackTrace();
            }

        }
        return conn;
    }
}
