package pojo;

public class Teacher {
    private Integer teacherID;
    private String name;
    private String phoneNumber;
    private String email;
    private String qq;
    private String description;
    private String encryptedPassword;

    public int getteacherID() {
        return teacherID;
    }

    public void setteacherID(Integer teacherID) {
        this.teacherID = teacherID;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getphoneNumber() {
        return phoneNumber;
    }

    public void setphoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getemail() {
        return email;
    }

    public void setemail(String email) {
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
