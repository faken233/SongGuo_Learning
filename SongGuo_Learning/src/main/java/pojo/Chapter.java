package pojo;

public class Chapter {
    private Integer chapterID;
    private Integer courseID;
    private String chapterName;
    private String content;

    public Chapter() {
    }

    public Chapter(Integer chapterID, Integer courseID, String chapterName, String content) {
        this.chapterID = chapterID;
        this.courseID = courseID;
        this.chapterName = chapterName;
        this.content = content;
    }

    public Integer getChapterID() {
        return chapterID;
    }

    public void setchapterID(Integer chapterID) {
        this.chapterID = chapterID;
    }

    public Integer getCourseID() {
        return courseID;
    }

    public void setcourseID(Integer courseID) {
        this.courseID = courseID;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setchapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public String getContent() {
        return content;
    }

    public void setcontent(String content) {
        this.content = content;
    }
}
