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
}
