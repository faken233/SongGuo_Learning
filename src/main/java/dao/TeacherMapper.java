package dao;

import pojo.Chapter;
import pojo.Course;
import pojo.EnrolledCourseMap;
import pojo.question.MultipleChoiceQuestion;
import pojo.question.ShortAnswerQuestion;
import pojo.question.TrueFalseQuestion;
import utils.mybatis.anno.InsertFaken;
import utils.mybatis.anno.ParamFaken;
import utils.mybatis.anno.SelectFaken;
import utils.mybatis.anno.UpdateFaken;

import java.sql.Timestamp;
import java.util.List;

public interface TeacherMapper {
    @UpdateFaken("update teachers set name = #{name}, email = #{email}, qq = #{qq}, description = #{description} where teacherID = #{teacherID}")
    int updateTeacher(@ParamFaken("name")        String name,
                      @ParamFaken("email")       String email,
                      @ParamFaken("qq")          String qq,
                      @ParamFaken("description") String description,
                      @ParamFaken("teacherID")   Integer id);

    @InsertFaken("insert into courses (courseID, teacherID, courseName, description, startDateTime, endDateTime, maxStudents) " +
            "values (#{courseID}, #{teacherID}, #{courseName}, #{description}, #{startDateTime}, #{endDateTime}, #{maxStudents})")
    int addNewCourse(   @ParamFaken("courseID")         Integer courseID,
                        @ParamFaken("teacherID")        Integer teacherID,
                        @ParamFaken("courseName")       String courseName,
                        @ParamFaken("description")      String description,
                        @ParamFaken("startDateTime")    Timestamp startDateTime,
                        @ParamFaken("endDateTime")      Timestamp endDateTime,
                        @ParamFaken("maxStudents")      Integer maxStudents);

    @SelectFaken("select * from courses where teacherID = #{teacherID}")
    List<Course> selectCoursesByTeacherID(@ParamFaken("teacherID") Integer teacherID);

    @SelectFaken("select * from chapters where courseID = #{courseID}")
    List<Chapter> selectChaptersByCourseID(@ParamFaken("courseID") Integer courseID);

    @InsertFaken("insert into chapters (chapterID, courseID, chapterName, content) " +
            "values (#{chapterID}, #{courseID}, #{chapterName}, #{content})")
    int addNewChapter(@ParamFaken("chapterID")   Integer chapterID,
                      @ParamFaken("courseID")    Integer courseID,
                      @ParamFaken("chapterName") String chapterName,
                      @ParamFaken("content")     String content);

    @InsertFaken("insert into truefalsequestions (questionID, chapterID, type, content, answer) " +
            "values(#{questionID}, #{chapterID}, #{type}, #{content}, #{answer})")
    int addTrueFalseQuestion(@ParamFaken("questionID")  Integer questionID,
                             @ParamFaken("chapterID")   Integer chapterID,
                             @ParamFaken("type")        Integer type,
                             @ParamFaken("content")     String content,
                             @ParamFaken("answer")      String answer);

    @InsertFaken("insert into shortanswerquestions (questionID, chapterID, type, content, answer) " +
            "values(#{questionID}, #{chapterID}, #{type}, #{content}, #{answer})")
    int addShortAnswerQuestion(@ParamFaken("questionID")    Integer questionID,
                             @ParamFaken("chapterID")       Integer chapterID,
                             @ParamFaken("type")            Integer type,
                             @ParamFaken("content")         String content,
                             @ParamFaken("answer")          String answer);

    @InsertFaken("insert into multiplechoicequestions (questionID, chapterID, type, content, answer, options) " +
            "values(#{questionID}, #{chapterID}, #{type}, #{content}, #{answer}, #{options})")
    int addMultipleChoiceQuestion(@ParamFaken("questionID")   Integer questionID,
                               @ParamFaken("chapterID")    Integer chapterID,
                               @ParamFaken("type")         Integer type,
                               @ParamFaken("content")      String content,
                               @ParamFaken("answer")       String answer,
                               @ParamFaken("options")      String options);

    @SelectFaken("select * from truefalsequestions where chapterID = #{chapterID}")
    List<TrueFalseQuestion> selectTrueFalseQuestionsByChapterID(@ParamFaken("chapterID") Integer chapterID);

    @SelectFaken("select * from shortanswerquestions where chapterID = #{chapterID}")
    List<ShortAnswerQuestion> selectShortAnswerQuestionsByChapterID(@ParamFaken("chapterID") Integer chapterID);

    @SelectFaken("select * from multiplechoicequestions where chapterID = #{chapterID}")
    List<MultipleChoiceQuestion> selectMultipleChoiceQuestionsByChapterID(@ParamFaken("chapterID") Integer chapterID);

    // 查询加入某课程的学生
    @SelectFaken("select * from enrolledcourses where courseID = #{courseID} limit #{offset}, #{pageSize}")
    List<EnrolledCourseMap> getEnrolledStudentsByCourseID(@ParamFaken("courseID")   Integer courseID,
                                                          @ParamFaken("offset")     Integer offset,
                                                          @ParamFaken("pageSize")   Integer pageSize);
}
