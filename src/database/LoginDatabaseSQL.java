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

            // Set the parameters for the query
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            // Execute the query
            try (java.sql.ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int userId = resultSet.getInt("user_id"); // Retrieve the userId
                    String role = resultSet.getString("role"); // Retrieve the role
                    System.out.println("Login successful! User ID: " + userId);

                    if ("Admin".equals(role)) {
                        // Open the admin main menu
                        userAggreement jframe = new userAggreement();
                        jframe.setVisible(true);

                        adminMenu jframe3 = new adminMenu();
                        jframe3.setVisible(true);
                        login.dispose();
                    } else if ("Staff".equals(role)) {
                        // Open the user main menu
                        userAggreement jframe = new userAggreement();
                        jframe.setVisible(true);

                        staffMenu jframe1 = new staffMenu();
                        jframe1.setVisible(true);
                        login.dispose();
                    }

                    else if ("Student".equals(role)) {
                        // Open the user main menu
                        userAggreement jframe = new userAggreement();
                        jframe.setVisible(true);

                        studentMenu jframe2 = new studentMenu();
                        jframe2.setVisible(true);
                        login.dispose();
                    }

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
