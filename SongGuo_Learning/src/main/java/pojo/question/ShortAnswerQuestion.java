package pojo.question;

public class ShortAnswerQuestion extends Question{
    private String answer;

    public ShortAnswerQuestion(Integer questionID, Integer chapterID, Integer type, String content, String answer) {
        super(questionID, chapterID, type, content);
        this.answer = answer;
    }

    public ShortAnswerQuestion() {
    }

    public String getAnswer() {
        return answer;
    }

    public void setanswer(String answer) {
        this.answer = answer;
    }

    @Override
    public void setquestionID(Integer questionID) {
        super.setquestionID(questionID);
    }

    @Override
    public void setchapterID(Integer chapterID) {
        super.setchapterID(chapterID);
    }

    @Override
    public void setcontent(String content) {
        super.setcontent(content);
    }

    @Override
    public void settype(Integer type) {
        super.settype(type);
    }
}
