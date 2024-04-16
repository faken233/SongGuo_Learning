package service;

import com.alibaba.fastjson.JSONObject;
import pojo.Student;
import pojo.Teacher;

public interface AccountService {
    Teacher validateIsNewTeacherAccount(String phoneNumber);
    Student validateIsNewStudentAccount(String phoneNumber);
    int createNewAccount(JSONObject newUser);
}
