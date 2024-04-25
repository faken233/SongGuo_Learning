package pojo;

import java.sql.Timestamp;

public class Course {
    private Integer courseID;
    private Integer teacherID;
    private String courseName;
    private String description;
    private Timestamp startDateTime;
    private Timestamp endDateTime;
    private Integer maxStudents;

    public int getCourseID() {
        return courseID;
    }

    public void setcourseID(Integer courseID) {
        this.courseID = courseID;
    }

    public Integer getTeacherID() {
        return teacherID;
    }

    public void setteacherID(Integer teacherID) {
        this.teacherID = teacherID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setcourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public void setdescription(String description) {
        this.description = description;
    }

    public Timestamp getStartDateTime() {
        return startDateTime;
    }

    public void setstartDateTime(Timestamp startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Timestamp getEndDateTime() {
        return endDateTime;
    }

    public void setendDateTime(Timestamp endDateTime) {
        this.endDateTime = endDateTime;
    }

    public Integer getMaxStudents() {
        return maxStudents;
    }

    public void setmaxStudents(Integer maxStudents) {
        this.maxStudents = maxStudents;
    }

    public Course() {
    }

    public Course(Integer courseID, Integer teacherID, String courseName, String description, Timestamp startDateTime, Timestamp endDateTime, Integer maxStudents) {
        this.courseID = courseID;
        this.teacherID = teacherID;
        this.courseName = courseName;
        this.description = description;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.maxStudents = maxStudents;
    }
}
