package pojo.question;

public class MultipleChoiceQuestion extends Question{
    private String options;
    private String answer;

    public MultipleChoiceQuestion(Integer questionID, Integer chapterID, Integer type, String content, String options, String answer) {
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

    @Override
    public void setchapterID(Integer chapterID) {
        super.setchapterID(chapterID);
    }
}

