package pojo;

public class ChapterLearningProgress {
    private Integer studentID;
    private Integer chapterID;
    private Integer answerCount;
    private Float accuracy;

    public ChapterLearningProgress() {
    }

    public ChapterLearningProgress(Integer studentID, Integer chapterID, Integer answerCount, Float accuracy) {
        this.studentID = studentID;
        this.chapterID = chapterID;
        this.answerCount = answerCount;
        this.accuracy = accuracy;
    }

    public Integer getStudentID() {
        return studentID;
    }

    public void setstudentID(Integer studentID) {
        this.studentID = studentID;
    }

    public Integer getChapterID() {
        return chapterID;
    }

    public void setchapterID(Integer chapterID) {
        this.chapterID = chapterID;
    }

    public Integer getAnswerCount() {
        return answerCount;
    }

    public void setanswerCount(Integer answerCount) {
        this.answerCount = answerCount;
    }

    public Float getAccuracy() {
        return accuracy;
    }

    public void setaccuracy(Float accuracy) {
        this.accuracy = accuracy;
    }
}
