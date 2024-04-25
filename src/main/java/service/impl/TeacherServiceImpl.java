package service.impl;

import constnum.ConstNum;
import dao.AccountMapper;
import dao.TeacherMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojo.*;
import pojo.question.MultipleChoiceQuestion;
import pojo.question.Question;
import pojo.question.ShortAnswerQuestion;
import pojo.question.TrueFalseQuestion;
import service.TeacherService;
import utils.mybatis.utils.MapperProxyFactory;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TeacherServiceImpl implements TeacherService {
    private static final Logger log = LoggerFactory.getLogger(TeacherServiceImpl.class);
    private final AccountMapper accountMapper = MapperProxyFactory.getMapper(AccountMapper.class);
    private final TeacherMapper teacherMapper = MapperProxyFactory.getMapper(TeacherMapper.class);

    @Override
    public Teacher getInfo(int id) {
        return accountMapper.getTeacherByID(id);
    }

    @Override
    public void updateInfo(Teacher teacher) {

        String name = teacher.getname();
        String email = teacher.getemail();
        String qq = teacher.getQq();
        String description = teacher.getDescription();
        Integer id = teacher.getteacherID();

        log.info("教师ID为{}的用户修改了个人信息", id);

        teacherMapper.updateTeacher(name, email, qq, description, id);
    }

    @Override
    public void addNewCourse(int teacherID, String courseName, String description, Timestamp startDateTime, Timestamp endDateTime, int maxStudents) {
        int courseID = generateCourseID();
        log.info("教师ID为{}的用户上线了新课程.课程ID为{}", teacherID, courseID);
        teacherMapper.addNewCourse(courseID, teacherID, courseName, description, startDateTime, endDateTime, maxStudents);
    }

    @Override
    public void deleteCourse(int courseID) {
        // 删除题目
        List<Chapter> chapters = teacherMapper.selectChaptersByCourseID(courseID);
        List<Integer> chapterIDs = new ArrayList<>();

        // 获得一个课程下章节ID的列表
        for (Chapter chapter : chapters) {
            chapterIDs.add(chapter.getChapterID());
        }

        // 遍历列表, 删除题目
        for (Integer chapterID : chapterIDs) {
            teacherMapper.deleteMultipleChoiceQuestionsByChapterID(chapterID);
            teacherMapper.deleteShortAnswerQuestionsByChapterID(chapterID);
            teacherMapper.deleteTrueFalseQuestionsByChapterID(chapterID);
        }

        // 再删除章节
        teacherMapper.deleteChaptersByCourseID(courseID);

        // 删除参与课程信息
        teacherMapper.deleteEnrolledCourse(courseID);

        // 最后删除课程信息
        teacherMapper.deleteCourse(courseID);

        log.info("ID为{}的课程被下线", courseID);
    }

    @Override
    public void deleteChapter(int chapterID) {
        teacherMapper.deleteMultipleChoiceQuestionsByChapterID(chapterID);
        teacherMapper.deleteShortAnswerQuestionsByChapterID(chapterID);
        teacherMapper.deleteTrueFalseQuestionsByChapterID(chapterID);
        teacherMapper.deleteChapterByChapterID(chapterID);

        log.info("ID为{}的章节被删除", chapterID);
    }

    public void deleteQuestion(int questionID) {
        if (String.valueOf(questionID).charAt(0) == '5') {
            teacherMapper.deleteTrueFalseQuestionsByQuestionID(questionID);
        } else if (String.valueOf(questionID).charAt(0) == '6') {
            teacherMapper.deleteMultipleChoiceQuestionsByQuestionID(questionID);
        } else teacherMapper.deleteShortAnswerQuestionsByQuestionID(questionID);

        log.info("ID为{}的题目被删除", questionID);
    }


    @Override
    public List<Course> selectCourses(int teacherID) {
        return teacherMapper.selectCoursesByTeacherID(teacherID);
    }

    @Override
    public List<Chapter> selectChaptersByCourseID(int courseID) {
        return teacherMapper.selectChaptersByCourseID(courseID);
    }

    @Override
    public void addNewChapter(Chapter chapter) {

        Integer chapterID = generateChapterID();
        Integer courseID = chapter.getCourseID();
        String chapterName = chapter.getChapterName();
        String content = chapter.getContent();

        log.info("ID为{}的课程新增了一个章节,章节ID为{}", courseID, chapterID);

        teacherMapper.addNewChapter(chapterID, courseID, chapterName, content);
    }

    @Override
    public void addQuestionToChapter(Question question, int type) {
        // 根据类型选择操作的mapper方法
        Integer questionID = generateQuestionID(type);
        Integer chapterID = question.getChapterID();
        String content = question.getContent();

        if (type == ConstNum.TrueFalseQuestion) {
            log.info("ID为{}的章节新增一个判断题, 题目ID为{}", chapterID, questionID);
            String answer = ((TrueFalseQuestion) question).getAnswer();
            teacherMapper.addTrueFalseQuestion(questionID, chapterID, type, content, answer);
        }
        else if (type == ConstNum.ShortAnswerQuestion) {
            log.info("ID为{}的章节新增一个简答题, 题目ID为{}", chapterID, questionID);
            String answer = ((ShortAnswerQuestion) question).getAnswer();
            teacherMapper.addShortAnswerQuestion(questionID, chapterID, type, content, answer);
        }
        else if (type == ConstNum.MultipleChoiceQuestion) {
            log.info("ID为{}的章节新增一个选择题, 题目ID为{}", chapterID, questionID);
            String answer = ((MultipleChoiceQuestion) question).getAnswer();
            String options = ((MultipleChoiceQuestion) question).getOptions();
            teacherMapper.addMultipleChoiceQuestion(questionID, chapterID, type, content, answer, options);
        }
    }

    @Override
    public List<Question> selectQuestionsByChapterID(int chapterID) {
        List<Question> questionList = new ArrayList<>();

        // 三个表都查一遍
        List<TrueFalseQuestion> trueFalseQuestions = teacherMapper.selectTrueFalseQuestionsByChapterID(chapterID);
        List<MultipleChoiceQuestion> multipleChoiceQuestions = teacherMapper.selectMultipleChoiceQuestionsByChapterID(chapterID);
        List<ShortAnswerQuestion> shortAnswerQuestions = teacherMapper.selectShortAnswerQuestionsByChapterID(chapterID);

        // 全部添加到超类的列表中
        questionList.addAll(trueFalseQuestions);
        questionList.addAll(multipleChoiceQuestions);
        questionList.addAll(shortAnswerQuestions);
        return questionList;
    }

    @Override
    public List<Student> getEnrolledStudentsByCourseID(int courseID, int currentPage, int pageSize) {
        List<Student> studentList = new ArrayList<>();

        // 偏移量
        int offset = (currentPage - 1) * pageSize;

        List<EnrolledCourseMap> enrolledStudents = teacherMapper.getEnrolledStudentsByCourseID(courseID, offset, pageSize);

        for (EnrolledCourseMap enrolledStudent : enrolledStudents) {
            // 根据enrolledStudent的学生ID查找学生
            Student studentByID = accountMapper.getStudentByID(enrolledStudent.getStudentID());
            studentList.add(studentByID);
        }

        return studentList;
    }

    private Integer generateQuestionID(int type) {
        String firstDigit = String.valueOf(type);

        // 随机生成八位数
        String randomPart = generateRandomDigits();

        // 拼接生成完整的ID
        return Integer.parseInt(firstDigit + randomPart);
    }

    private int generateChapterID() {
        String firstDigit = String.valueOf(ConstNum.Chapter);

        // 随机生成八位数
        String randomPart = generateRandomDigits();

        // 拼接生成完整的ID
        return Integer.parseInt(firstDigit + randomPart);
    }

    public int generateCourseID(){
        String firstDigit = String.valueOf(ConstNum.Course);

        // 随机生成八位数
        String randomPart = generateRandomDigits();

        // 拼接生成完整的ID
        return Integer.parseInt(firstDigit  + randomPart);
    }

    private String generateRandomDigits() {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder(4);
        for (int i = 0; i < 8; i++) {
            stringBuilder.append(random.nextInt(10)); // 生成0到9之间的随机数字
        }
        return stringBuilder.toString();
    }
}
