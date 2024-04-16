package pojo;

public class Student {
    private Integer studentID;
    private String name;
    private String grade;
    private String className;
    private String phoneNumber;
    private String encryptedPassword;

    public Integer getStudentID() {
        return studentID;
    }

    public void setStudentID(Integer studentID) {
        this.studentID = studentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public Student(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Student(Integer studentID, String name, String grade, String className, String phoneNumber, String encryptedPassword) {
        this.studentID = studentID;
        this.name = name;
        this.grade = grade;
        this.className = className;
        this.phoneNumber = phoneNumber;
        this.encryptedPassword = encryptedPassword;
    }

    public Student() {
    }
}
