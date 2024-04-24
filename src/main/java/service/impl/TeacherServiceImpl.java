package service.impl;

import constnum.ConstNum;
import dao.AccountMapper;
import dao.TeacherMapper;
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

        teacherMapper.updateTeacher(name, email, qq, description, id);
    }

    @Override
    public void addNewCourse(int teacherID, String courseName, String description, Timestamp startDateTime, Timestamp endDateTime, int maxStudents) {
        int courseID = generateCourseID();
        teacherMapper.addNewCourse(courseID, teacherID, courseName, description, startDateTime, endDateTime, maxStudents);
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

        teacherMapper.addNewChapter(chapterID, courseID, chapterName, content);
    }

    @Override
    public void addQuestionToChapter(Question question, int type) {
        // 根据类型选择操作的mapper方法
        Integer questionID = generateQuestionID(type);
        Integer chapterID = question.getChapterID();
        String content = question.getContent();

        if (type == ConstNum.TrueFalseQuestion) {
            String answer = ((TrueFalseQuestion) question).getAnswer();
            teacherMapper.addTrueFalseQuestion(questionID, chapterID, type, content, answer);
        }
        else if (type == ConstNum.ShortAnswerQuestion) {
            String answer = ((ShortAnswerQuestion) question).getAnswer();
            teacherMapper.addShortAnswerQuestion(questionID, chapterID, type, content, answer);
        }
        else if (type == ConstNum.MultipleChoiceQuestion) {
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
