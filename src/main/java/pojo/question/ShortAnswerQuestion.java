package pojo.question;

public class ShortAnswerQuestion extends Question{
    private String answer;

    public ShortAnswerQuestion(Integer questionID, Integer chapterID, int type, String content, String answer) {
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
}
