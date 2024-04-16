package pojo;

public class SaltMap {
    private String salt;
    private String userID;

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public SaltMap() {
    }

    public SaltMap(String salt, String userID) {
        this.salt = salt;
        this.userID = userID;
    }
}
