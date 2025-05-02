package database;

import model.BorrowModel;
import model.IssueModel;
import model.ProjectorModel;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
                ImageIcon icon = new ImageIcon(getClass().getResource("/images/logoresize.jpg")); // Load your ima
                JOptionPane.showMessageDialog(null, "Projector added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE, icon);

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
                ImageIcon icon = new ImageIcon(getClass().getResource("/images/logoresize.jpg")); // Load your ima
                JOptionPane.showMessageDialog(null, "Failed to add projector.", "Error", JOptionPane.ERROR_MESSAGE, icon);
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
                ImageIcon icon = new ImageIcon(getClass().getResource("/images/logoresize.jpg")); // Load your ima
                JOptionPane.showMessageDialog(null, "Projector deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE, icon);

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
                ImageIcon icon = new ImageIcon(getClass().getResource("/images/logoresize.jpg")); // Load your ima
                JOptionPane.showMessageDialog(null, "Failed to delete projector.", "Error", JOptionPane.ERROR_MESSAGE, icon);
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
            System.out.println("Error deleting projector: " + e.getMessage());
        }
    }

    public List<ProjectorModel> getProjectorsAvailable() {
        List<ProjectorModel> projectors = new ArrayList<>();
        String sql = "SELECT * FROM projector_table WHERE status = 'Available'";

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
            System.out.println("Error retrieving available projectors: " + e.getMessage());
        }

        return projectors;

    }

    public List<ProjectorModel> getProjectorsUnvailable() {
        List<ProjectorModel> projectors = new ArrayList<>();
        String sql = "SELECT * FROM projector_table WHERE status = 'Unavailable'";

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
            System.out.println("Error retrieving available projectors: " + e.getMessage());
        }

        return projectors;

    }

    public void borrowProjector(String studentName, String studentID, String course, String projectorName) {
        String sql = "UPDATE projector_table SET status = 'Unavailable' WHERE projector_name = ?";
        String insertLogs = "INSERT INTO log_table (description) VALUES (?)";
        String insertBorrowHistory = "INSERT INTO borrowing_table (name, student_id, year_and_course, projector_name) VALUES (?, ?, ?, ?)";

        try (java.sql.Connection connection = java.sql.DriverManager.getConnection(
                MYSQLConnection.databaseUrl, MYSQLConnection.user, MYSQLConnection.password);
             java.sql.PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, projectorName);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Projector borrowed successfully.");
                JOptionPane.showMessageDialog(null, "Projector borrowed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);

                // Insert log entry
                try (java.sql.PreparedStatement logStatement = connection.prepareStatement(insertLogs)) {
                    logStatement.setString(1, "Projector " + projectorName + " borrowed by student " + studentName + " (" + studentID + ") for course " + course);

                    int  logRowsAffected = logStatement.executeUpdate();

                    if (logRowsAffected > 0) {
                        System.out.println("Log entry added successfully.");

                        try (java.sql.PreparedStatement borrowStatement = connection.prepareStatement(insertBorrowHistory)) {
                            borrowStatement.setString(1, studentName);
                            borrowStatement.setString(2, studentID);
                            borrowStatement.setString(3, course);
                            borrowStatement.setString(4, projectorName);

                            int borrowRowsAffected = borrowStatement.executeUpdate();

                            if (borrowRowsAffected > 0) {
                                System.out.println("Borrowing history updated successfully.");
                            } else {
                                System.out.println("Failed to update borrowing history.");
                            }
                        } catch (java.sql.SQLException e) {
                            e.printStackTrace();
                            System.out.println("Error inserting borrowing history: " + e.getMessage());
                        }
                    } else {
                        System.out.println("Failed to add log entry.");
                    }
                } catch (java.sql.SQLException e) {
                    e.printStackTrace();
                    System.out.println("Error inserting log: " + e.getMessage());
                }
            } else {
                System.out.println("Failed to borrow projector.");
                ImageIcon icon = new ImageIcon(getClass().getResource("/images/logoresize.jpg")); // Load your image
                JOptionPane.showMessageDialog(null, "Failed to borrow projector.", "Error", JOptionPane.ERROR_MESSAGE, icon);
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
            System.out.println("Error borrowing projector: " + e.getMessage());
        }
    }

    public void insertReturnProjector(String projectorName, String studentName, String studentId, String yearCourseSection) {
        String sql = "UPDATE projector_table SET status = 'Available' WHERE projector_name = ?";
        String insertLogs = "INSERT INTO log_table (description) VALUES (?)";
        String insertReturnHistory = "INSERT INTO return_table (name, student_id, year_and_course, projector_name) VALUES (?, ?, ?, ?)";

        try (java.sql.Connection connection = java.sql.DriverManager.getConnection(
                MYSQLConnection.databaseUrl, MYSQLConnection.user, MYSQLConnection.password);
             java.sql.PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, projectorName);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Projector returned successfully.");
                ImageIcon icon = new ImageIcon(getClass().getResource("/images/logoresize.jpg")); // Load your image
                JOptionPane.showMessageDialog(null, "Projector returned successfully.", "Success", JOptionPane.INFORMATION_MESSAGE, icon);

                // Insert log entry
                try (java.sql.PreparedStatement logStatement = connection.prepareStatement(insertLogs)) {
                    logStatement.setString(1, "Projector " + projectorName + " returned by student " + studentName + " (" + studentId + ") for course " + yearCourseSection);
                    logStatement.executeUpdate();
                } catch (java.sql.SQLException e) {
                    e.printStackTrace();
                    System.out.println("Error inserting log: " + e.getMessage());
                }

                // Insert return history
                try (java.sql.PreparedStatement returnStatement = connection.prepareStatement(insertReturnHistory)) {
                    returnStatement.setString(1, studentName);
                    returnStatement.setString(2, studentId);
                    returnStatement.setString(3, yearCourseSection);
                    returnStatement.setString(4, projectorName);

                    int returnRowsAffected = returnStatement.executeUpdate();

                    if (returnRowsAffected > 0) {
                        System.out.println("Return history updated successfully.");
                    } else {
                        System.out.println("Failed to update return history.");
                    }
                } catch (java.sql.SQLException e) {
                    e.printStackTrace();
                    System.out.println("Error inserting return history: " + e.getMessage());
                }
            } else {
                System.out.println("Failed to return projector.");
                ImageIcon icon = new ImageIcon(getClass().getResource("/images/logoresize.jpg")); // Load your image
                JOptionPane.showMessageDialog(null, "Failed to return projector.", "Error", JOptionPane.ERROR_MESSAGE, icon);
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
            System.out.println("Error returning projector: " + e.getMessage());
        }
    }

    public void sendRequest(String description) {
        String sql = "INSERT INTO request_table (description) VALUES (?)";
        String insertLogs = "INSERT INTO log_table (description) VALUES (?)";

        try (java.sql.Connection connection = java.sql.DriverManager.getConnection(
                MYSQLConnection.databaseUrl, MYSQLConnection.user, MYSQLConnection.password);
             java.sql.PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, description);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Request sent successfully.");
                ImageIcon icon = new ImageIcon(getClass().getResource("/images/logoresize.jpg")); // Load your image
                JOptionPane.showMessageDialog(null, "Request sent successfully.", "Success", JOptionPane.INFORMATION_MESSAGE, icon);

                // Insert log entry
                try (java.sql.PreparedStatement logStatement = connection.prepareStatement(insertLogs)) {
                    logStatement.setString(1, "Request sent: " + description);
                    logStatement.executeUpdate();
                } catch (java.sql.SQLException e) {
                    e.printStackTrace();
                    System.out.println("Error inserting log: " + e.getMessage());
                }
            } else {
                System.out.println("Failed to send request.");
                ImageIcon icon = new ImageIcon(getClass().getResource("/images/logoresize.jpg")); // Load your image
                JOptionPane.showMessageDialog(null, "Failed to send request.", "Error", JOptionPane.ERROR_MESSAGE, icon);
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
            System.out.println("Error sending request: " + e.getMessage());
        }
    }

    public void insertIssue(Map<String, String> data) {

        String sql = "INSERT INTO issue_projector (projector_name, issue) VALUES ( ?, ?)";
        String insertLog = "INSERT INTO log_table (description) VALUES ( ?)";

        try (Connection conn = DriverManager.getConnection(MYSQLConnection.databaseUrl, MYSQLConnection.user, MYSQLConnection.password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, data.get("projector_name"));
            pstmt.setString(2, data.get("issue"));

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Issue inserted successfully.");

                ImageIcon icon = new ImageIcon(getClass().getResource("/images/logoresize.jpg")); // Load your image
                JOptionPane.showMessageDialog(null, "Issue inserted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE, icon);


                // Insert into log_table
                try (PreparedStatement pstmt2 = conn.prepareStatement(insertLog)) {
                    pstmt2.setString(1, "Issue logged: " + data.get("issue"));

                    pstmt2.executeUpdate();
                }
            }
            else {
                System.out.println("No issue found with the given user ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<IssueModel> getIssue() {
        List<IssueModel> issues = new ArrayList<>();
        String sql = "SELECT * FROM issue_projector";

        try (java.sql.Connection connection = java.sql.DriverManager.getConnection(
                MYSQLConnection.databaseUrl, MYSQLConnection.user, MYSQLConnection.password);
             java.sql.PreparedStatement preparedStatement = connection.prepareStatement(sql);
             java.sql.ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String id = resultSet.getString("projector_id");
                String projectorName = resultSet.getString("projector_name");
                String issue = resultSet.getString("issue");

                IssueModel issueModel = new IssueModel(id, projectorName, issue);
                issues.add(issueModel);
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
            System.out.println("Error retrieving issues: " + e.getMessage());
        }

        return issues;
    }

    public void updateToAvailable(String projectorID, String status) {
        String sql = "UPDATE projector_table SET status = ? WHERE projector_id = ?";
        String insertLogs = "INSERT INTO log_table (description) VALUES (?)";

        try (java.sql.Connection connection = java.sql.DriverManager.getConnection(
                MYSQLConnection.databaseUrl, MYSQLConnection.user, MYSQLConnection.password);
             java.sql.PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, status);
            preparedStatement.setString(2, projectorID);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Projector status updated successfully.");
                ImageIcon icon = new ImageIcon(getClass().getResource("/images/logoresize.jpg")); // Load your image
                JOptionPane.showMessageDialog(null, "Projector status updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE, icon);

                // Insert log entry
                try (java.sql.PreparedStatement logStatement = connection.prepareStatement(insertLogs)) {
                    logStatement.setString(1, "Projector with ID " + projectorID + " status updated to " + status);
                    logStatement.executeUpdate();
                } catch (java.sql.SQLException e) {
                    e.printStackTrace();
                    System.out.println("Error inserting log: " + e.getMessage());
                }
            } else {
                System.out.println("Failed to update projector status.");
                ImageIcon icon = new ImageIcon(getClass().getResource("/images/logoresize.jpg")); // Load your image
                JOptionPane.showMessageDialog(null, "Failed to update projector status.", "Error", JOptionPane.ERROR_MESSAGE, icon);     }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
            System.out.println("Error updating projector status: " + e.getMessage());
        }
    }

    public List<BorrowModel> getBorrowingHistory() {
        List<BorrowModel> borrowList = new ArrayList<>();
        String sql = "SELECT * FROM borrowing_table";

        try (java.sql.Connection connection = java.sql.DriverManager.getConnection(
                MYSQLConnection.databaseUrl, MYSQLConnection.user, MYSQLConnection.password);
             java.sql.PreparedStatement preparedStatement = connection.prepareStatement(sql);
             java.sql.ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String id = resultSet.getString("borrow_id");
                String studentName = resultSet.getString("name");
                String studentID = resultSet.getString("student_id");
                String yearSection = resultSet.getString("year_and_course");
                String projectorName = resultSet.getString("projector_name");

                BorrowModel borrowModel = new BorrowModel(id, studentName, studentID, yearSection, projectorName);
                borrowList.add(borrowModel);
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
            System.out.println("Error retrieving borrowing history: " + e.getMessage());
        }

        return borrowList;
    }
}
