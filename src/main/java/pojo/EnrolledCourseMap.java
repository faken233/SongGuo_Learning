package pojo;

import java.sql.Timestamp;

public class EnrolledCourseMap {
    private Integer courseID;
    private Integer studentID;

    public Integer getCourseID() {
        return courseID;
    }

    public void setcourseID(Integer courseID) {
        this.courseID = courseID;
    }

    public Integer getStudentID() {
        return studentID;
    }

    public void setstudentID(Integer studentID) {
        this.studentID = studentID;
    }

    public Timestamp getEnrollDateTime() {
        return enrollDateTime;
    }

    public void setenrollDateTime(Timestamp enrollDateTime) {
        this.enrollDateTime = enrollDateTime;
    }

    public EnrolledCourseMap(Integer courseID, Integer studentID, Timestamp enrollDateTime) {
        this.courseID = courseID;
        this.studentID = studentID;
        this.enrollDateTime = enrollDateTime;
    }

    private Timestamp enrollDateTime;

    public EnrolledCourseMap() {
    }
}
