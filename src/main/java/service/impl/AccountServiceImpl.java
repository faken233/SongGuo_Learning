package service.impl;

import com.alibaba.fastjson.JSONObject;
import constnum.ConstNum;
import dao.AccountMapper;
import pojo.Student;
import pojo.Teacher;
import service.AccountService;
import utils.mybatis.utils.MapperProxyFactory;

import java.util.Random;
public class AccountServiceImpl implements AccountService {

    // 引入mapper
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

        // 获取JSONObject的type属性值, 然后移除此属性
        Integer accountType = newUser.getInteger("type");
        newUser.remove("type");

        // 分类型操作
        if (accountType == 1) {
            //1是教师 校验
            if (newUser.getInteger("teacherCertificate").equals(ConstNum.teacherCertificate)) {

                // 通过认证, 处理数据, 解析JSONObject
                int id = Integer.parseInt(generateID(true));
                accountMapper.insertNewTeacher(id,
                        newUser.getString("name"),
                        newUser.getString("phoneNumber"),
                        newUser.getString("password")
                );
                return id;
            } else {
                // 返回-1说明用户没有输入正确的教师凭证
                return -1;
            }
        }else if (accountType == 2) {
            //2为学生
            int id = Integer.parseInt(generateID(false));
            // 解析JSONObject
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
        // 根据类型去查找对应用户数据库, 没有相应数据则返回null
        return Integer.parseInt(type) == ConstNum.Teacher ? accountMapper.getTeacherByID(Integer.parseInt(userID)) : accountMapper.getStudentByID(Integer.parseInt(userID));
    }

    @Override
    public Object loginConfirmation(String userid, String password, String type) {
        // 账户密码校验
        if (Integer.parseInt(type) == ConstNum.Teacher) {
            return accountMapper.getTeacherByIDAndPassword(Integer.parseInt(userid), password);
        }else if (Integer.parseInt(type) == ConstNum.Student) {
            return accountMapper.getStudentByIDAndPassword(Integer.parseInt(userid), password);
        }else return null;
    }

    public String generateID(boolean isTeacher) {
        // 根据老师或学生生成ID的第一位
        String firstDigit = isTeacher ? "1" : "2";

        // 随机生成八位数
        String randomPart = generateRandomDigits();

        // 拼接生成完整的ID
        return firstDigit + randomPart;
    }

    // 生成指定位数的随机数字字符串
    private String generateRandomDigits() {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder(4);
        for (int i = 0; i < 8; i++) {
            stringBuilder.append(random.nextInt(10)); // 生成0到9之间的随机数字
        }
        return stringBuilder.toString();
    }
}
