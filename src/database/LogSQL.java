package database;

import model.LogModel;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LogSQL {

    private static  volatile  LogSQL instance;

    public static LogSQL getInstance() {
        if (instance == null) {
            synchronized (LogSQL.class) {
                if (instance == null) {
                    instance = new LogSQL();
                }
            }
        }
        return instance;
    }


    public List<LogModel> getAllLogs() {
        String query = "SELECT * FROM log_table";

        List<LogModel> logList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(MYSQLConnection.databaseUrl, MYSQLConnection.user, MYSQLConnection.password);
                java.sql.Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)) {

                while (resultSet.next()) {
                    String logID = resultSet.getString("log_id");
                    String description = resultSet.getString("description");

                    LogModel logModel = new LogModel(logID, description);
                    logList.add(logModel);
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return logList;
    }
}
