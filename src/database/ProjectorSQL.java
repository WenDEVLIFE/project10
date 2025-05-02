package database;

import javax.swing.*;

public class ProjectorSQL {

    private static  volatile ProjectorSQL instance;

    public static ProjectorSQL getInstance() {
        if (instance == null) {
            synchronized (ProjectorSQL.class) {
                if (instance == null) {
                    instance = new ProjectorSQL();
                }
            }
        }
        return instance;
    }


    public void addProjector(String projectorName) {
        String sql = "INSERT INTO projector_table (projector_name, status) VALUES (?, ?)";

        try (java.sql.Connection connection = java.sql.DriverManager.getConnection(
                MYSQLConnection.databaseUrl, MYSQLConnection.user, MYSQLConnection.password);
             java.sql.PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, projectorName);
            preparedStatement.setString(2, "Available"); // Assuming the default status is "Available"

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Projector added successfully.");
                JOptionPane.showMessageDialog(null, "Projector added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                System.out.println("Failed to add projector.");
                JOptionPane.showMessageDialog(null, "Failed to add projector.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
            System.out.println("Error adding projector: " + e.getMessage());
        }
    }
}
