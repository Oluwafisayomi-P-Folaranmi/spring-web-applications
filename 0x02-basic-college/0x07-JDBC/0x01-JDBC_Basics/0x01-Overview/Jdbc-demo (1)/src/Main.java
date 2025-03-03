import java.sql.*;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {

        /**
         * Database properties
         **/
        String url = "jdbc:mysql://localhost:3306/college-db";
        String username = "springstudent";
        String password = "springstudent";

        /**
         * Database connectivity
         **/
        Connection connection = DriverManager.getConnection(url, username, password);
        String sql = "SELECT first_name FROM student WHERE student_id = 104";

        /**
         * Create statement
         **/
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.next();

        String resultSetString = resultSet.getString(1);
        System.out.println(resultSetString);

    }
}