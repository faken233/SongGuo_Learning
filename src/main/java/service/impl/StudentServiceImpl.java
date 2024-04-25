package service.impl;

import dao.AccountMapper;
import dao.StudentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojo.*;
import pojo.question.MultipleChoiceQuestion;
import pojo.question.TrueFalseQuestion;
import service.StudentService;
import utils.mybatis.utils.MapperProxyFactory;

import java.util.ArrayList;
import java.util.List;

public class StudentServiceImpl implements StudentService {

    private static final Logger log = LoggerFactory.getLogger(StudentServiceImpl.class);
    private final AccountMapper accountMapper = MapperProxyFactory.getMapper(AccountMapper.class);
    private final StudentMapper studentMapper = MapperProxyFactory.getMapper(StudentMapper.class);

    @Override
    public Student getInfo(int id) {
        try {
            return accountMapper.getStudentByID(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateInfo(Student student) {

        String studentName = student.getName();
        String grade = student.getGrade();
        String className = student.getClassName();
        Integer studentID = student.getStudentID();

        log.info("学生ID为{}的用户修改了个人信息", studentID);

        try {
            studentMapper.updateStudent(studentName, grade, className, studentID);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Course> selectParticipatableCourses(int studentID) {
        // 所有在时间内的课程
        try {
            List<Course> courses = studentMapper.selectParticipatableCourses();

            // 学生已经参与过的课程
            List<EnrolledCourseMap> enrolledCourseMaps = studentMapper.selectAlreadyParticipatedCourses(studentID);

            // courses里面剔除掉enrolledCourseMaps含有的课程
            // 1.拿到所有参与过的课程ID
            List<Integer> enrolledCourseIDs = new ArrayList<>();
            for (EnrolledCourseMap enrolledCourseMap : enrolledCourseMaps) {
                enrolledCourseIDs.add(enrolledCourseMap.getCourseID());
            }

            // 2.遍历courses, 剔除ID包括在enrolledCourseIDs里面的课程
            courses.removeIf(course -> enrolledCourseIDs.contains(course.getCourseID()));

            // 得到所有可参与的课程courses
            return courses;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int participateCourse(int studentID, int courseID) {
        List<EnrolledCourseMap> enrolledCourseMaps = studentMapper.selectAlreadyParticipatedCoursesByCourseID(courseID);
        Course course = studentMapper.selectCourseByCourseID(courseID);
        int maxStudents = course.getMaxStudents();
        int enrolledCount = enrolledCourseMaps.size();
        if (enrolledCount >= maxStudents) {
            return 0;
        } else {
            log.info("学生ID为{}的学生选择参加课程ID为{}的课程", studentID, courseID);
            studentMapper.participateCourse(studentID, courseID);
            return 1;
        }
    }

    @Override
    public List<Course> selectParticipatedCourses(int studentID) {
        // 所有在时间内的课程
        List<Course> courses = studentMapper.selectParticipatableCourses();

        // 学生已经参与过的课程
        List<EnrolledCourseMap> enrolledCourseMaps = studentMapper.selectAlreadyParticipatedCourses(studentID);

        // 学生参与过的课程ID的集合
        List<Integer> enrolledCourseIDs = new ArrayList<>();
        for (EnrolledCourseMap enrolledCourseMap : enrolledCourseMaps) {
            enrolledCourseIDs.add(enrolledCourseMap.getCourseID());
        }

        //courses里面根据Maps取得学生参与过的课程
        courses.removeIf(course -> !(enrolledCourseIDs.contains(course.getCourseID())));

        return courses;
    }

    @Override
    public ChapterLearningProgress selectChapterLearningProgressByStudentIDAndChapterID(Integer studentID, Integer chapterID) {
        return studentMapper.selectChapterLearningProgressByStudentIDAndChapterID(studentID, chapterID);
    }

    @Override
    public ChapterLearningProgress checkChapterAnswers(List<StudentAnswer> studentAnswers, Integer chapterID) {
        // 学生上传答案, 比对后记录正确与否, 统计数据后返回学习情况, 同时将情况存入数据库

        // 初始化学习情况
        Integer studentID = studentAnswers.get(0).getStudentID();
        ChapterLearningProgress chapterLearningProgress = new ChapterLearningProgress();
        chapterLearningProgress.setstudentID(studentID);
        chapterLearningProgress.setchapterID(chapterID);

        // 遍历提交题目列表, 对比答案设置Correction
        float totalCountExceptSAQuestion = 0;
        float correctCount = 0;
        float totalSACount = 0;
        for (StudentAnswer studentAnswer : studentAnswers) {
            Integer questionID = studentAnswer.getQuestionID();
            String studentAnswerContent = studentAnswer.getAnswer();
            int type = studentAnswer.getType();
            String refAnswer;
            switch (type) {
                case 5:
                    // 取出题库的题
                    TrueFalseQuestion trueFalseQuestion = studentMapper.selectTrueFalseQuestionByQuestionID(questionID);
                    // 除开简答题的题数自增
                    totalCountExceptSAQuestion++;
                    // 获取参考答案
                    refAnswer = trueFalseQuestion.getAnswer();
                    if (refAnswer.equals(studentAnswerContent)) {
                        // 答案相等
                        correctCount++;
                        studentAnswer.setcorrection("true");
                    } else studentAnswer.setcorrection("false");
                    // 添加一条学生答题记录
                    studentMapper.addStudentAnswerRecord(studentID, questionID, studentAnswerContent, studentAnswer.getCorrection(), type);
                    break;
                case 6:
                    MultipleChoiceQuestion multipleChoiceQuestion = studentMapper.selectMultipleChoiceQuestionByQuestionID(questionID);
                    totalCountExceptSAQuestion++;
                    refAnswer = multipleChoiceQuestion.getAnswer();
                    if (refAnswer.equals(studentAnswerContent)) {
                        correctCount++;
                        studentAnswer.setcorrection("true");
                    } else studentAnswer.setcorrection("false");
                    studentMapper.addStudentAnswerRecord(studentID, questionID, studentAnswerContent, studentAnswer.getCorrection(), type);
                    break;
                case 7:
                    // 简答题不必设置答案对错
                    totalSACount++;
                    studentAnswer.setcorrection("REF_ONLY");
                    studentMapper.addStudentAnswerRecord(studentID, questionID, studentAnswerContent, studentAnswer.getCorrection(), type);
                    break;
                default:
            }
        }
        // 正确率不涉及简答题
        float accuracy = correctCount / totalCountExceptSAQuestion;
        chapterLearningProgress.setaccuracy(accuracy);
        chapterLearningProgress.setanswerCount((int) (totalSACount + totalCountExceptSAQuestion));

        studentMapper.addStudentChapterLearningProgress(
                chapterLearningProgress.getStudentID(),
                chapterLearningProgress.getChapterID(),
                chapterLearningProgress.getAnswerCount(),
                chapterLearningProgress.getAccuracy());

        return chapterLearningProgress;
    }
}































