package model;

public class BorrowModel {

    String id;

    String studentName;

    String studentID ;

    String yearSection;

    String projectorName;

    public BorrowModel(String id, String studentName, String studentID, String yearSection, String projectorName) {
        this.id = id;
        this.studentName = studentName;
        this.studentID = studentID;
        this.yearSection = yearSection;
        this.projectorName = projectorName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getYearSection() {
        return yearSection;
    }

    public void setYearSection(String yearSection) {
        this.yearSection = yearSection;
    }

    public String getProjectorName() {
        return projectorName;
    }

    public void setProjectorName(String projectorName) {
        this.projectorName = projectorName;
    }

    @Override
    public String toString() {
        return "BorrowModel{" + "id=" + id + ", studentName=" + studentName + ", studentID=" + studentID + ", yearSection=" + yearSection + ", projectorName=" + projectorName + '}';
    }
}
