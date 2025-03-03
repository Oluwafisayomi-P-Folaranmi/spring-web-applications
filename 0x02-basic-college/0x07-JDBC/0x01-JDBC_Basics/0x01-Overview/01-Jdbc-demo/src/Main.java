import java.sql.*;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        /** Database properties **/
        String url = "jdbc:mysql://localhost:3306/college-db";
        String username = "springstudent";
        String password = "springstudent";

        try {
            // 1. Get a connection to the database
            connection = DriverManager.getConnection(url, username, password);

            // 2. Create a statement
            statement = connection.createStatement();

            // 3. Execute SQL query
            String sql = "SELECT * FROM student";
            resultSet = statement.executeQuery(sql);

            // 4. Process the result set
            while(resultSet.next()) {

                System.out.println(resultSet.getString("first_name")
                        + " " + resultSet.getString("last_name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }

            if (resultSet != null) {
                resultSet.close();
            }

            if (connection != null) {
                connection.close();
            }

        }
    }
}
