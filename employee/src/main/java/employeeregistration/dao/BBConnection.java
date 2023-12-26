package employeeregistration.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class BBConnection {

	private static Connection connection;
    private static String dbAddress = "jdbc:mysql://localhost:3306/Employee";
    private static String user="root";
    private static String password="5294";

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
    	 Class.forName("com.mysql.cj.jdbc.Driver");
    	 if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(dbAddress, user, password);
        }
        return connection;
    }
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        BBConnection.getConnection();
    }
}

