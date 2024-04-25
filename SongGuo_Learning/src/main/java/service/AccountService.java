package service;

import com.alibaba.fastjson.JSONObject;
import pojo.Student;
import pojo.Teacher;

public interface AccountService {
    // 校验是否在注册新的教师用户, 凭证为手机号码
    Teacher validateIsNewTeacherAccount(String phoneNumber);

    // 校验是否在注册新的教师用户, 凭证为手机号码
    Student validateIsNewStudentAccount(String phoneNumber);

    // 传入一个用户信息, 内包含用户类型, 创建对应新用户
    int createNewAccount(JSONObject newUser);

    // 登陆校验, 首先检查是否存在该用户
    Object loginValidation(String userID, String type);

    // 再次校验, 比对账户和密码
    Object loginConfirmation(String userid, String password, String type);
}
