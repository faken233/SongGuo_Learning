package pojo.question;

public class Question {
    private Integer questionID;
    private Integer chapterID;
    private int type;
    private String content;


    public Question(Integer questionID, Integer chapterID, int type, String content) {
        this.questionID = questionID;
        this.chapterID = chapterID;
        this.type = type;
        this.content = content;
    }

    public Question() {
    }

    public Integer getQuestionID() {
        return questionID;
    }

    public void setquestionID(Integer questionID) {
        this.questionID = questionID;
    }

    public Integer getChapterID() {
        return chapterID;
    }

    public void setchapterID(Integer chapterID) {
        this.chapterID = chapterID;
    }

    public int getType() {
        return type;
    }

    public void settype(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setcontent(String content) {
        this.content = content;
    }
}
