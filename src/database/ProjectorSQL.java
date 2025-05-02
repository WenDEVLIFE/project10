package database;

import model.ProjectorModel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

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


    // Add a projector to the database
    public void addProjector(String projectorName) {
        String sql = "INSERT INTO projector_table (projector_name, status) VALUES (?, ?)";
        String insertLogs = "INSERT INTO log_table (description) VALUES (?)";

        try (java.sql.Connection connection = java.sql.DriverManager.getConnection(
                MYSQLConnection.databaseUrl, MYSQLConnection.user, MYSQLConnection.password);
             java.sql.PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, projectorName);
            preparedStatement.setString(2, "Available"); // Assuming the default status is "Available"

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Projector added successfully.");
                JOptionPane.showMessageDialog(null, "Projector added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);

                // Insert log entry
                try (java.sql.PreparedStatement logStatement = connection.prepareStatement(insertLogs)) {
                    logStatement.setString(1, "Projector " + projectorName + " added successfully.");
                    logStatement.executeUpdate();
                } catch (java.sql.SQLException e) {
                    e.printStackTrace();
                    System.out.println("Error inserting log: " + e.getMessage());
                }
            } else {
                System.out.println("Failed to add projector.");
                JOptionPane.showMessageDialog(null, "Failed to add projector.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
            System.out.println("Error adding projector: " + e.getMessage());
        }
    }

    public List<ProjectorModel> getProjectors() {
        List<ProjectorModel> projectors = new ArrayList<>();
        String sql = "SELECT * FROM projector_table";

        try (java.sql.Connection connection = java.sql.DriverManager.getConnection(
                MYSQLConnection.databaseUrl, MYSQLConnection.user, MYSQLConnection.password);
             java.sql.PreparedStatement preparedStatement = connection.prepareStatement(sql);
             java.sql.ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String projectorID = resultSet.getString("projector_id");
                String projectorName = resultSet.getString("projector_name");
                String status = resultSet.getString("status");

                ProjectorModel projector = new ProjectorModel(projectorID, projectorName, status);
                projectors.add(projector);
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
            System.out.println("Error retrieving projectors: " + e.getMessage());
        }

        return projectors;
    }

    // Added delete function to remove a projector from the database
    public void deleteProjector(String projectorId) {
        String sql = "DELETE FROM projector_table WHERE projector_id = ?";
        String insertLogs = "INSERT INTO log_table (description) VALUES (?)";

        try (java.sql.Connection connection = java.sql.DriverManager.getConnection(
                MYSQLConnection.databaseUrl, MYSQLConnection.user, MYSQLConnection.password);
             java.sql.PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, projectorId);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Projector deleted successfully.");
                JOptionPane.showMessageDialog(null, "Projector deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);

                // Insert log entry
                try (java.sql.PreparedStatement logStatement = connection.prepareStatement(insertLogs)) {
                    logStatement.setString(1, "Projector with ID " + projectorId + " deleted successfully.");
                    logStatement.executeUpdate();
                } catch (java.sql.SQLException e) {
                    e.printStackTrace();
                    System.out.println("Error inserting log: " + e.getMessage());
                }
            } else {
                System.out.println("Failed to delete projector.");
                JOptionPane.showMessageDialog(null, "Failed to delete projector.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
            System.out.println("Error deleting projector: " + e.getMessage());
        }
    }
}
