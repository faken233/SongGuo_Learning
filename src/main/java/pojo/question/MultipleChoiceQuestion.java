package pojo.question;

public class MultipleChoiceQuestion extends Question{
    private String options;
    private String answer;

    public MultipleChoiceQuestion(Integer questionID, Integer chapterID, int type, String content, String options, String answer) {
        super(questionID, chapterID, type, content);
        this.options = options;
        this.answer = answer;
    }

    public MultipleChoiceQuestion() {
    }

    public String getOptions() {
        return options;
    }

    public void setoptions(String options) {
        this.options = options;
    }

    public String getAnswer() {
        return answer;
    }

    public void setanswer(String answer) {
        this.answer = answer;
    }
}
