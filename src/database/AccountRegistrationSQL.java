package database;

import java.sql.*;
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
}
