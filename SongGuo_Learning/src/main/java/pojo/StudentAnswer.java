package pojo;

import java.sql.Timestamp;

public class StudentAnswer {
    private Integer studentID;
    private Integer questionID;
    private String answer;
    private Timestamp answerDateTime;
    private String correction;
    private Integer type;

    public StudentAnswer() {
    }

    public StudentAnswer(Integer studentID, Integer questionID, String answer, Timestamp answerDateTime, String correction, Integer type) {
        this.studentID = studentID;
        this.questionID = questionID;
        this.answer = answer;
        this.answerDateTime = answerDateTime;
        this.correction = correction;
        this.type = type;
    }

    public Integer getStudentID() {
        return studentID;
    }

    public Integer getType() {
        return type;
    }

    public void settype(Integer type) {
        this.type = type;
    }

    public void setstudentID(Integer studentID) {
        this.studentID = studentID;
    }

    public Integer getQuestionID() {
        return questionID;
    }

    public void setquestionID(Integer questionID) {
        this.questionID = questionID;
    }

    public String getAnswer() {
        return answer;
    }

    public void setanswer(String answer) {
        this.answer = answer;
    }

    public Timestamp getAnswerDateTime() {
        return answerDateTime;
    }

    public void setanswerDateTime(Timestamp answerDateTime) {
        this.answerDateTime = answerDateTime;
    }

    public String getCorrection() {
        return correction;
    }

    public void setcorrection(String correction) {
        this.correction = correction;
    }
}
