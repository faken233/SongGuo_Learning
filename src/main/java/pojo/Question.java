package pojo;

public class Question {
    private String questionID;
    private String chapterID;
    private int type;
    private String answer;
    private String content;

    public Question() {
    }

    public Question(String questionID, String chapterID, int type, String answer, String content) {
        this.questionID = questionID;
        this.chapterID = chapterID;
        this.type = type;
        this.answer = answer;
        this.content = content;
    }

    public String getQuestionID() {
        return questionID;
    }

    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }

    public String getChapterID() {
        return chapterID;
    }

    public void setChapterID(String chapterID) {
        this.chapterID = chapterID;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
