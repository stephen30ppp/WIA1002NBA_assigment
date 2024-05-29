package views;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/login_schema";
    private static final String USER = "root";
    private static final String PASSWORD = "MySQL";

    public User getUserByUsername(String username) throws SQLException {
        User user = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();
            String query = "SELECT * FROM users WHERE username = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, username);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user = new User(
                        resultSet.getString("username"),
                        resultSet.getString("password")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection, statement, resultSet);
        }
        return user;
    }

    public boolean validateUser(String username, String password) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        try  {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }




    public void saveUser(User user) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            String query = "INSERT INTO users (username, password) VALUES (?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection, statement, null);
        }
    }

    public List<String> getAllUsernames() {
        List<String> usernames = new ArrayList<>();
        String query = "SELECT username FROM users";
        Connection connection = null;
        try  {
            connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                usernames.add(resultSet.getString("username"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usernames;
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }

    private static void closeConnection(Connection connection, PreparedStatement statement, ResultSet resultSet) throws SQLException {
        if (resultSet != null) {
            resultSet.close();
        }
        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }


}
