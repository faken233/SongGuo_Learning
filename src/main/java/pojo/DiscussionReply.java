package pojo;

import java.sql.Timestamp;

@SuppressWarnings("unused")
public class DiscussionReply {
    private Integer courseID;
    private Integer replierID;
    private String replierName;
    private String content;
    private Timestamp replyDateTime;

    public DiscussionReply() {
    }

    public DiscussionReply(Integer courseID, Integer replierID, String replierName, String content, Timestamp replyDateTime) {
        this.courseID = courseID;
        this.replierID = replierID;
        this.replierName = replierName;
        this.content = content;
        this.replyDateTime = replyDateTime;
    }

    public Integer getCourseID() {
        return courseID;
    }

    public void setcourseID(Integer courseID) {
        this.courseID = courseID;
    }

    public Integer getReplierID() {
        return replierID;
    }

    public void setreplierID(Integer replierID) {
        this.replierID = replierID;
    }

    public String getReplierName() {
        return replierName;
    }

    public void setreplierName(String replierName) {
        this.replierName = replierName;
    }

    public String getContent() {
        return content;
    }

    public void setcontent(String content) {
        this.content = content;
    }

    public Timestamp getReplyDateTime() {
        return replyDateTime;
    }

    public void setreplyDateTime(Timestamp replyDateTime) {
        this.replyDateTime = replyDateTime;
    }
}
