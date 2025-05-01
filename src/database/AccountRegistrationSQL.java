package database;

import model.UserModel;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AccountRegistrationSQL {
    private static volatile  AccountRegistrationSQL instance;

    public static AccountRegistrationSQL getInstance() {
        if (instance == null) {
            synchronized (AccountRegistrationSQL.class) {
                if (instance == null) {
                    instance = new AccountRegistrationSQL();
                }
            }
        }
        return instance;
    }

    // This will check if username exist
    public boolean checkIfUsernameExist(String username) {
        String sql = "SELECT COUNT(*) FROM users WHERE username = ?";

        try (Connection conn = DriverManager.getConnection(MYSQLConnection.databaseUrl, MYSQLConnection.user, MYSQLConnection.password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0; // Check if the count is greater than 0
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Return false if an exception occurs or no result is found
    }

    // Insert student account
    public void insertStudentAccount(Map<String, Object> userdata) {
        String sql = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
        String insertStudent = "INSERT INTO student (user_id, student_id) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(MYSQLConnection.databaseUrl, MYSQLConnection.user, MYSQLConnection.password);
             PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            // Insert into student_account table
            pstmt.setString(1, (String) userdata.get("username"));
            pstmt.setString(2, (String) userdata.get("password"));
            pstmt.setString(3, "Student");
            pstmt.executeUpdate();

            // Retrieve the generated user_id
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int userId = generatedKeys.getInt(1); // Get the generated user_id

                    // Insert into users table
                    try (PreparedStatement pstmt2 = conn.prepareStatement(insertStudent)) {
                        pstmt2.setInt(1, userId);
                        pstmt2.setString(2, (String) userdata.get("student_id")); // Assuming student_id is provided in userdata
                        pstmt2.executeUpdate();
                    }
                } else {
                    throw new SQLException("Failed to retrieve generated user_id.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Insert staff account
    public void insertStaffAccount(Map<String, Object> userdata, JDialog dialog){
        String sql = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
        String insertStudent = "INSERT INTO student (user_id, student_id) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(MYSQLConnection.databaseUrl, MYSQLConnection.user, MYSQLConnection.password);
             PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            // Insert into student_account table
            pstmt.setString(1, (String) userdata.get("username"));
            pstmt.setString(2, (String) userdata.get("password"));
            pstmt.setString(3, "Staff");

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                // Retrieve the generated user_id
               dialog.dispose();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

        // Insert admin account
    public void insertAdminAccount(Map<String, Object> userdata, JDialog dialog){
        String sql = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(MYSQLConnection.databaseUrl, MYSQLConnection.user, MYSQLConnection.password);
             PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            // Insert into student_account table
            pstmt.setString(1, (String) userdata.get("username"));
            pstmt.setString(2, (String) userdata.get("password"));
            pstmt.setString(3, "Admin");
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                // Retrieve the generated user_id
                dialog.dispose();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<UserModel> getUserAccount(){
        String sql = "SELECT * FROM users";
        List<UserModel> userList = new ArrayList<>();

        try (java.sql.Connection connection = java.sql.DriverManager.getConnection(
                MYSQLConnection.databaseUrl, MYSQLConnection.user, MYSQLConnection.password);
             java.sql.PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            // Execute the query
            try (java.sql.ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String id = resultSet.getString("user_id");
                    String username = resultSet.getString("username");
                    String password = resultSet.getString("password");
                    String role = resultSet.getString("role");

                    UserModel userModel = new UserModel(id, username, password, role);
                    userList.add(userModel);
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return userList;

    }

    public void deleteAccount(int userId) {
        String sql = "DELETE FROM users WHERE user_id = ?";
        try (Connection conn = DriverManager.getConnection(MYSQLConnection.databaseUrl, MYSQLConnection.user, MYSQLConnection.password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Account deleted successfully.");
                JOptionPane .showMessageDialog(null, "Account deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                System.out.println("No account found with the given user ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
