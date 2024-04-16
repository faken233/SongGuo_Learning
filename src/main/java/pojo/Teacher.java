package pojo;

public class Teacher {
    private int teacherID;
    private String name;
    private String phoneNumber;
    private String email;
    private String qq;
    private String description;
    private String encryptedPassword;

    public int getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public Teacher() {
    }

    public Teacher(int teacherID, String name, String phoneNumber, String email, String qq, String description, String encryptedPassword) {
        this.teacherID = teacherID;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.qq = qq;
        this.description = description;
        this.encryptedPassword = encryptedPassword;
    }
}
