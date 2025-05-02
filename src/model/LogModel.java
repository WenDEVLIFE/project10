package model;

public class LogModel {
    String logID;

    String description;

    public LogModel(String logID, String description) {
        this.logID = logID;
        this.description = description;
    }

    public String getLogID() {
        return logID;
    }

    public void setLogID(String logID) {
        this.logID = logID;
    }

    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "LogModel{" +
                "logID='" + logID + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
