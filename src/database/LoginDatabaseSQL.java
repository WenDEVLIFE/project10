package database;

import UI.*;

import javax.swing.*;

public class LoginDatabaseSQL {
    private static volatile LoginDatabaseSQL instance;

    public static LoginDatabaseSQL getInstance() {
        if (instance == null) {
            synchronized (LoginDatabaseSQL.class) {
                if (instance == null) {
                    instance = new LoginDatabaseSQL();
                }
            }
        }
        return instance;
    }

    public void LoginUser(String username, String password, login login) {
        String loginSQL = "SELECT user_id, role FROM users WHERE username = ? AND password = ?";
        try (java.sql.Connection connection = java.sql.DriverManager.getConnection(
                MYSQLConnection.databaseUrl, MYSQLConnection.user, MYSQLConnection.password);
             java.sql.PreparedStatement preparedStatement = connection.prepareStatement(loginSQL)) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            try (java.sql.ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int userId = resultSet.getInt("user_id");
                    String role = resultSet.getString("role");
                    System.out.println("Login successfully");

                    userAggreement jframe = new userAggreement(role);
                    jframe.setVisible(true);
                    login.dispose(); // Close the login window

                    ImageIcon icon = new ImageIcon(getClass().getResource("/images/logoresize.jpg")); // Load your ima
                    javax.swing.JOptionPane.showMessageDialog(login, "Login successful! User ID: " + username, "Login Successful", javax.swing.JOptionPane.INFORMATION_MESSAGE, icon);


                } else {
                    System.out.println("Invalid username or password.");
                    javax.swing.JOptionPane.showMessageDialog(login, "Invalid username or password.", "Login Failed", javax.swing.JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
            System.out.println("An error occurred during login: " + e.getMessage());
            javax.swing.JOptionPane.showMessageDialog(login, "An error occurred during login: " + e.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

}
