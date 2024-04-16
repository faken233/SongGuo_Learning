package service.impl;

import com.alibaba.fastjson.JSONObject;
import constnum.ConstNum;
import dao.AccountMapper;
import pojo.SaltMap;
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
                String id = generateID(true);
                accountMapper.insertNewSaltMap(Integer.valueOf(id), newUser.getString("salt"));
                return accountMapper.insertNewTeacher(Integer.valueOf(id),
                        newUser.getString("name"),
                        newUser.getString("phoneNumber"),
                        newUser.getString("password")
                );
            }
        }else if (accountType == 2) {
            //2为学生
            String id = generateID(false);
            accountMapper.insertNewSaltMap(Integer.valueOf(id), newUser.getString("salt"));
            return accountMapper.insertNewStudent(Integer.valueOf(id),
                    newUser.getString("name"),
                    newUser.getString("phoneNumber"),
                    newUser.getString("password")
            );
        }
        return 0;
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
