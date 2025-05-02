package model;

public class ProjectorModel {

    String id;

    String projectorName;

    String status;

    public ProjectorModel(String id, String projectorName, String status) {
        this.id = id;
        this.projectorName = projectorName;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectorName() {
        return projectorName;
    }

    public void setProjectorName(String projectorName) {
        this.projectorName = projectorName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ProjectorModel{" +
                "id='" + id + '\'' +
                ", projectorName='" + projectorName + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
