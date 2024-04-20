package pojo.question;

public class TrueFalseQuestion extends Question{
    private String answer;

    public TrueFalseQuestion(Integer questionID, Integer chapterID, int type, String content, String answer) {
        super(questionID, chapterID, type, content);
        this.answer = answer;
    }

    public TrueFalseQuestion() {
    }

    public String getAnswer() {
        return answer;
    }

    public void setanswer(String answer) {
        this.answer = answer;
    }

    @Override
    public void setchapterID(Integer chapterID) {
        super.setchapterID(chapterID);
    }

    @Override
    public void setquestionID(Integer questionID) {
        super.setquestionID(questionID);
    }

    @Override
    public void settype(Integer type) {
        super.settype(type);
    }

    @Override
    public void setcontent(String content) {
        super.setcontent(content);
    }
}
