package model;

public class IssueModel {
    String id;
    String projectorName;
    String issue;


    public IssueModel(String id, String projectorName, String issue) {
        this.id = id;
        this.projectorName = projectorName;
        this.issue = issue;
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

    public String getIssue() {
        return issue;
    }


    public void setIssue(String issue) {
        this.issue = issue;
    }

    @Override
    public String toString() {
        return "IssueModel{" +
                "id='" + id + '\'' +
                ", projectorName='" + projectorName + '\'' +
                ", issue='" + issue + '\'' +
                '}';
    }

}
