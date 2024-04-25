package dao;

import pojo.Chapter;
import pojo.Course;
import pojo.EnrolledCourseMap;
import pojo.question.MultipleChoiceQuestion;
import pojo.question.ShortAnswerQuestion;
import pojo.question.TrueFalseQuestion;
import utils.mybatis.anno.*;

import java.sql.Timestamp;
import java.util.List;

@SuppressWarnings("ALL")
public interface TeacherMapper {

    // 更新教师数据
    @UpdateFaken("update teachers set name = #{name}, email = #{email}, qq = #{qq}, description = #{description} where teacherID = #{teacherID}")
    void updateTeacher(@ParamFaken("name")        String name,
                      @ParamFaken("email")       String email,
                      @ParamFaken("qq")          String qq,
                      @ParamFaken("description") String description,
                      @ParamFaken("teacherID")   Integer id);

    // 新增课程
    @InsertFaken("insert into courses (courseID, teacherID, courseName, description, startDateTime, endDateTime, maxStudents) " +
            "values (#{courseID}, #{teacherID}, #{courseName}, #{description}, #{startDateTime}, #{endDateTime}, #{maxStudents})")
    void addNewCourse(   @ParamFaken("courseID")         Integer courseID,
                        @ParamFaken("teacherID")        Integer teacherID,
                        @ParamFaken("courseName")       String courseName,
                        @ParamFaken("description")      String description,
                        @ParamFaken("startDateTime")    Timestamp startDateTime,
                        @ParamFaken("endDateTime")      Timestamp endDateTime,
                        @ParamFaken("maxStudents")      Integer maxStudents);

    // 删除课程
    @DeleteFaken("delete from courses where courseID = #{courseID}")
    void deleteCourse(@ParamFaken("courseID") Integer courseID);

    // 删除学生参加课程的信息
    @DeleteFaken("delete from enrolledcourses where courseID = #{courseID}")
    void deleteEnrolledCourse(@ParamFaken("courseID") Integer courseID);

    // 删除课程下的章节
    @DeleteFaken("delete from chapters where courseID = #{courseID}")
    void deleteChaptersByCourseID(@ParamFaken("courseID") Integer courseID);

    // 根据章节ID删除章节
    @DeleteFaken("delete from chapters where chapterID = #{chapterID}")
    void deleteChapterByChapterID(@ParamFaken("chapterID") Integer chapterID);

    // 根据章节删除判断题
    @DeleteFaken("delete from truefalsequestions where chapterID = #{chapterID}")
    void deleteTrueFalseQuestionsByChapterID(@ParamFaken("chapterID") Integer chapterID);

    // 根据章节删除简答题
    @DeleteFaken("delete from shortanswerquestions where chapterID = #{chapterID}")
    void deleteShortAnswerQuestionsByChapterID(@ParamFaken("chapterID") Integer chapterID);

    // 根据章节删除选择题
    @DeleteFaken("delete from multiplechoicequestions where chapterID = #{chapterID}")
    void deleteMultipleChoiceQuestionsByChapterID(@ParamFaken("chapterID") Integer chapterID);

    // 删除一道判断题
    @DeleteFaken("delete from truefalsequestions where questionID = #{questionID}")
    void deleteTrueFalseQuestionsByQuestionID(@ParamFaken("questionID") Integer questionID);

    // 删除一道选择题
    @DeleteFaken("delete from multiplechoicequestions where questionID = #{questionID}")
    void deleteMultipleChoiceQuestionsByQuestionID(@ParamFaken("questionID") Integer questionID);

    // 删除一道简答题
    @DeleteFaken("delete from shortanswerquestions where questionID = #{questionID}")
    void deleteShortAnswerQuestionsByQuestionID(@ParamFaken("questionID") Integer questionID);

    // 根据教师ID查找其名下的课程
    @SelectFaken("select * from courses where teacherID = #{teacherID}")
    List<Course> selectCoursesByTeacherID(@ParamFaken("teacherID") Integer teacherID);

    // 根据课程ID查找其包含的章节
    @SelectFaken("select * from chapters where courseID = #{courseID}")
    List<Chapter> selectChaptersByCourseID(@ParamFaken("courseID") Integer courseID);

    // 新增章节
    @InsertFaken("insert into chapters (chapterID, courseID, chapterName, content) " +
            "values (#{chapterID}, #{courseID}, #{chapterName}, #{content})")
    void addNewChapter(@ParamFaken("chapterID")   Integer chapterID,
                      @ParamFaken("courseID")    Integer courseID,
                      @ParamFaken("chapterName") String chapterName,
                      @ParamFaken("content")     String content);

    // 添加一道判断题
    @InsertFaken("insert into truefalsequestions (questionID, chapterID, type, content, answer) " +
            "values(#{questionID}, #{chapterID}, #{type}, #{content}, #{answer})")
    void addTrueFalseQuestion(@ParamFaken("questionID")  Integer questionID,
                             @ParamFaken("chapterID")   Integer chapterID,
                             @ParamFaken("type")        Integer type,
                             @ParamFaken("content")     String content,
                             @ParamFaken("answer")      String answer);

    // 添加一道简答题
    @InsertFaken("insert into shortanswerquestions (questionID, chapterID, type, content, answer) " +
            "values(#{questionID}, #{chapterID}, #{type}, #{content}, #{answer})")
    void addShortAnswerQuestion(@ParamFaken("questionID")    Integer questionID,
                             @ParamFaken("chapterID")       Integer chapterID,
                             @ParamFaken("type")            Integer type,
                             @ParamFaken("content")         String content,
                             @ParamFaken("answer")          String answer);

    // 添加一道选择题
    @InsertFaken("insert into multiplechoicequestions (questionID, chapterID, type, content, answer, options) " +
            "values(#{questionID}, #{chapterID}, #{type}, #{content}, #{answer}, #{options})")
    void addMultipleChoiceQuestion(@ParamFaken("questionID")   Integer questionID,
                               @ParamFaken("chapterID")    Integer chapterID,
                               @ParamFaken("type")         Integer type,
                               @ParamFaken("content")      String content,
                               @ParamFaken("answer")       String answer,
                               @ParamFaken("options")      String options);

    // 查找某章节下的判断题
    @SelectFaken("select * from truefalsequestions where chapterID = #{chapterID}")
    List<TrueFalseQuestion> selectTrueFalseQuestionsByChapterID(@ParamFaken("chapterID") Integer chapterID);

    // 查找某章节下的简答题
    @SelectFaken("select * from shortanswerquestions where chapterID = #{chapterID}")
    List<ShortAnswerQuestion> selectShortAnswerQuestionsByChapterID(@ParamFaken("chapterID") Integer chapterID);

    // 查找某章节下的选择题
    @SelectFaken("select * from multiplechoicequestions where chapterID = #{chapterID}")
    List<MultipleChoiceQuestion> selectMultipleChoiceQuestionsByChapterID(@ParamFaken("chapterID") Integer chapterID);

    // 查询加入某课程的学生
    @SelectFaken("select * from enrolledcourses where courseID = #{courseID} limit #{offset}, #{pageSize}")
    List<EnrolledCourseMap> getEnrolledStudentsByCourseID(@ParamFaken("courseID")   Integer courseID,
                                                          @ParamFaken("offset")     Integer offset,
                                                          @ParamFaken("pageSize")   Integer pageSize);
}
