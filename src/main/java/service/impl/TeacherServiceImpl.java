package service.impl;

import constnum.ConstNum;
import dao.AccountMapper;
import dao.TeacherMapper;
import pojo.Chapter;
import pojo.Course;
import pojo.Teacher;
import pojo.question.MultipleChoiceQuestion;
import pojo.question.Question;
import pojo.question.ShortAnswerQuestion;
import pojo.question.TrueFalseQuestion;
import service.TeacherService;
import utils.mybatis.utils.MapperProxyFactory;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    public int updateInfo(Teacher teacher) {
        String name = teacher.getname();
        String email = teacher.getemail();
        String qq = teacher.getQq();
        String description = teacher.getDescription();
        Integer id = teacher.getteacherID();

        return teacherMapper.updateTeacher(name, email, qq, description, id);
    }

    @Override
    public int addNewCourse(int teacherID, String courseName, String description, Timestamp startDateTime, Timestamp endDateTime, int maxStudents) {
        int courseID = generateCourseID();
        return teacherMapper.addNewCourse(courseID, teacherID, courseName, description, startDateTime, endDateTime, maxStudents);
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
    public int addNewChapter(Chapter chapter) {
        Integer chapterID = generateChapterID();
        Integer courseID = chapter.getCourseID();
        String chapterName = chapter.getChapterName();
        String content = chapter.getContent();
        return teacherMapper.addNewChapter(chapterID, courseID, chapterName, content);
    }

    @Override
    public int addQuestionToChapter(Question question, int type) {
        Integer questionID = generateQuestionID(type);
        Integer chapterID = question.getChapterID();
        String content = question.getContent();
        int i = 0;
        if (type == ConstNum.TrueFalseQuestion) {
            String answer = ((TrueFalseQuestion) question).getAnswer();
            i = teacherMapper.addTrueFalseQuestion(questionID, chapterID, type, content, answer);
        } else if (type == ConstNum.ShortAnswerQuestion) {
            String answer = ((ShortAnswerQuestion) question).getAnswer();
            i = teacherMapper.addShortAnswerQuestion(questionID, chapterID, type, content, answer);
        } else if (type == ConstNum.MultipleChoiceQuestion) {
            String answer = ((MultipleChoiceQuestion) question).getAnswer();
            String options = ((MultipleChoiceQuestion) question).getOptions();
            i = teacherMapper.addMultipleChoiceQuestion(questionID, chapterID, type, content, answer, options);
        }
        return i;
    }

    @Override
    public List<Question> selectQuestionsByChapterID(int chapterID) {
        List<Question> questionList = new ArrayList<>();
        List<TrueFalseQuestion> trueFalseQuestions = teacherMapper.selectTrueFalseQuestionsByChapterID(chapterID);
        List<MultipleChoiceQuestion> multipleChoiceQuestions = teacherMapper.selectMultipleChoiceQuestionsByChapterID(chapterID);
        List<ShortAnswerQuestion> shortAnswerQuestions = teacherMapper.selectShortAnswerQuestionsByChapterID(chapterID);
        questionList.addAll(trueFalseQuestions);
        questionList.addAll(multipleChoiceQuestions);
        questionList.addAll(shortAnswerQuestions);
        return questionList;
    }

    private Integer generateQuestionID(int type) {
        String firstDigit = String.valueOf(type);

        // 根据当前时间生成接下来六位
        LocalDateTime now = LocalDateTime.now();
        String timePart = now.format(DateTimeFormatter.ofPattern("yyMMdd"));

        // 随机生成二位数
        String randomPart = generateRandomDigits();

        // 拼接生成完整的ID
        return Integer.parseInt(firstDigit + timePart + randomPart);
    }

    private int generateChapterID() {
        String firstDigit = String.valueOf(ConstNum.Chapter);

        // 根据当前时间生成接下来六位
        LocalDateTime now = LocalDateTime.now();
        String timePart = now.format(DateTimeFormatter.ofPattern("yyMMdd"));

        // 随机生成二位数
        String randomPart = generateRandomDigits();

        // 拼接生成完整的ID
        return Integer.parseInt(firstDigit + timePart + randomPart);
    }

    public int generateCourseID(){
        String firstDigit = String.valueOf(ConstNum.Course);

        // 根据当前时间生成接下来六位
        LocalDateTime now = LocalDateTime.now();
        String timePart = now.format(DateTimeFormatter.ofPattern("yyMMdd"));

        // 随机生成二位数
        String randomPart = generateRandomDigits();

        // 拼接生成完整的ID
        return Integer.parseInt(firstDigit + timePart + randomPart);
    }

    private String generateRandomDigits() {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder(4);
        for (int i = 0; i < 2; i++) {
            stringBuilder.append(random.nextInt(10)); // 生成0到9之间的随机数字
        }
        return stringBuilder.toString();
    }
}
