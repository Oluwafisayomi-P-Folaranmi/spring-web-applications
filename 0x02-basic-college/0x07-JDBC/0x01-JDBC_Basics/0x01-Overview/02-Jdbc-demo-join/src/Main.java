import java.sql.*;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {

        System.out.println("-------------------------------------------------------");
        System.out.println();
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
            String joinSql
                    = "SELECT student.student_id, student.first_name, " +
                        "student.last_name, student.contact_number, " +
                        "department.department_id, department.department_name " +
                      "FROM student " +
                      "LEFT JOIN department " +
                      "ON student.department_id = department.department_id; ";
            resultSet = statement.executeQuery(joinSql);

            // 4. Process the result set
            while(resultSet.next()) {

                System.out.println(resultSet.getString("student_id") + " | " +
                        resultSet.getString("student.first_name") + " | " +
                        resultSet.getString("student.last_name") + " | " +
                        resultSet.getString("student.contact_number") + " | " +
                        resultSet.getString("department.department_id") + " | " +
                        resultSet.getString("department.department_name"));
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

        System.out.println();
    }
}
