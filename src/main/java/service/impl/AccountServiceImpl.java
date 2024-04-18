package service.impl;

import com.alibaba.fastjson.JSONObject;
import constnum.ConstNum;
import dao.AccountMapper;
import pojo.Student;
import pojo.Teacher;
import service.AccountService;
import utils.mybatis.utils.MapperProxyFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
public class AccountServiceImpl implements AccountService {

    private final AccountMapper accountMapper = MapperProxyFactory.getMapper(AccountMapper.class);

    @Override
    public Teacher validateIsNewTeacherAccount(String phoneNumber) {
        return accountMapper.getTeacherByPhoneNumber(phoneNumber);
    }

    @Override
    public Student validateIsNewStudentAccount(String phoneNumber) {
        return accountMapper.getStudentByPhoneNumber(phoneNumber);
    }

    @Override
    public int createNewAccount(JSONObject newUser) {
        Integer accountType = newUser.getInteger("type");
        newUser.remove("type");
        if (accountType == 1) {
            //1是教师 校验
            if (newUser.getInteger("teacherCertificate").equals(ConstNum.teacherCertificate)) {
                // 通过认证, 处理数据

                int id = Integer.parseInt(generateID(true));
                accountMapper.insertNewTeacher(id,
                        newUser.getString("name"),
                        newUser.getString("phoneNumber"),
                        newUser.getString("password")
                );
                return id;
            } else {
                return -1;
            }
        }else if (accountType == 2) {
            //2为学生
            int id = Integer.parseInt(generateID(false));
            accountMapper.insertNewStudent(id,
                    newUser.getString("name"),
                    newUser.getString("phoneNumber"),
                    newUser.getString("password")
            );
            return id;
        }
        return 0;
    }

    @Override
    public Object loginValidation(String userID, String type) {
        return Integer.parseInt(type) == ConstNum.Teacher ? accountMapper.getTeacherByID(Integer.parseInt(userID)) : accountMapper.getStudentByID(Integer.parseInt(userID));
    }

    @Override
    public Object loginConfirmation(String userid, String password, String type) {
        if (Integer.parseInt(type) == ConstNum.Teacher) {
            return accountMapper.getTeacherByIDAndPassword(Integer.parseInt(userid), password);
        }else if (Integer.parseInt(type) == ConstNum.Student) {
            return accountMapper.getStudentByIDAndPassword(Integer.parseInt(userid), password);
        }else return null;
    }

    public String generateID(boolean isTeacher) {
        // 根据老师或学生生成ID的第一位
        String firstDigit = isTeacher ? "1" : "2";

        // 根据当前时间生成接下来六位
        LocalDateTime now = LocalDateTime.now();
        String timePart = now.format(DateTimeFormatter.ofPattern("yyMMdd"));

        // 随机生成二位数
        String randomPart = generateRandomDigits();

        // 拼接生成完整的ID
        return firstDigit + timePart + randomPart;
    }

    // 生成指定位数的随机数字字符串
    private String generateRandomDigits() {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder(4);
        for (int i = 0; i < 2; i++) {
            stringBuilder.append(random.nextInt(10)); // 生成0到9之间的随机数字
        }
        return stringBuilder.toString();
    }
}
