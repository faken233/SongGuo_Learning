package dao;

import pojo.ChapterLearningProgress;
import pojo.Course;
import pojo.EnrolledCourseMap;
import pojo.question.MultipleChoiceQuestion;
import pojo.question.ShortAnswerQuestion;
import pojo.question.TrueFalseQuestion;
import utils.mybatis.anno.InsertFaken;
import utils.mybatis.anno.ParamFaken;
import utils.mybatis.anno.SelectFaken;
import utils.mybatis.anno.UpdateFaken;

import java.util.List;

public interface StudentMapper {

    @UpdateFaken("update students set name = #{name}, grade = #{grade}, className = #{className} where studentID = #{studentID}")
    int updateStudent(@ParamFaken("name") String name, @ParamFaken("grade") String grade, @ParamFaken("className") String className, @ParamFaken("studentID") Integer id);

    // 查找在开课时间内的可参与的课程
    @SelectFaken("select * from courses where endDateTime > NOW()")
    List<Course> selectParticipatableCourses();

    // 查找指定学生已经参与过的课程ID
    @SelectFaken("select * from enrolledcourses where studentID = #{studentID}")
    List<EnrolledCourseMap> selectAlreadyParticipatedCourses(@ParamFaken("studentID") Integer studentID);

    // 参与新课程
    @InsertFaken("insert into enrolledcourses (studentID, courseID, enrollDateTime) values(#{studentID}, #{courseID}, NOW())")
    int participateCourse(@ParamFaken("studentID") Integer studentID, @ParamFaken("courseID") Integer courseID);

    // 查找某个学生某个章节的学习情况
    @SelectFaken("select * from chapterlearningprogress where studentID = #{studentID} and chapterID = #{chapterID}")
    ChapterLearningProgress selectChapterLearningProgressByStudentIDAndChapterID
    (@ParamFaken("studentID") Integer studentID, @ParamFaken("chapterID") Integer chapterID);

    // 比对答案(判断题), 查询题目
    @SelectFaken("select * from truefalsequestions where questionID = #{questionID}")
    TrueFalseQuestion selectTrueFalseQuestionByQuestionID(@ParamFaken("questionID") Integer questionID);

    // 比对答案(判断题), 查询题目
    @SelectFaken("select * from multiplechoicequestions where questionID = #{questionID}")
    MultipleChoiceQuestion selectMultipleChoiceQuestionByQuestionID(@ParamFaken("questionID") Integer questionID);

    // 比对答案(简答题), 查询题目
    @SelectFaken("select * from shortanswerquestions where questionID = #{questionID}")
    ShortAnswerQuestion selectShortAnswerQuestionByQuestionID(@ParamFaken("questionID") Integer questionID);

    // 录入一条学生答题记录
    @InsertFaken("insert into studentanswers (studentID, questionID, answerDateTime, answer, correction, type) " +
            "values (#{studentID}, #{questionID}, NOW(), #{answer}, #{correction}, #{type})")
    void addStudentAnswerRecord(@ParamFaken("studentID")    Integer studentID,
                                @ParamFaken("questionID")   Integer questionID,
                                @ParamFaken("answer")       String answer,
                                @ParamFaken("correction")   String correction,
                                @ParamFaken("type")         Integer type);

    // 录入某个学生某一个章节的答题情况
    @InsertFaken("insert into chapterlearningprogress (studentID, chapterID, answerCount, accuracy) " +
            "values (#{studentID}, #{chapterID}, #{answerCount}, #{accuracy})")
    void addStudentChapterLearningProgress(@ParamFaken("studentID")     Integer studentID,
                                           @ParamFaken("chapterID")     Integer chapterID,
                                           @ParamFaken("answerCount")   Integer answerCount,
                                           @ParamFaken("accuracy")      Float accuracy);
}
