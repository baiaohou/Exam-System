package com.lagou.server;

import com.lagou.model.Student;
import com.lagou.model.User;
import com.lagou.studentDB.StudentDB;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author baiaohou
 * @create 2020-07-28 7:47 PM
 * data access object
 * 编程实现数据的存取
 */
public class ServerDao {

    private static String tempUserName = null;
    private static String tempPassword = null;

    /**
     * 实现管理员账号密码的校验 并将结果返回出去
     */
    public boolean serverManagerCheck(User user) {
        if ("admin".equals(user.getUserName()) && "123456".equals(user.getPassword())) {
            return true;
        }
        return false;
    }

    public boolean serverStudentUsernamePasswordCheck(User user) throws IOException, ClassNotFoundException {
        ArrayList<Student> db = StudentDB.getDb();
        System.out.println("Database is " + db);
        for (Student s : db) {
            if (s.getUsername().equals(user.getUserName())) {
                if (s.getPassword().equals(user.getPassword())) {
                    tempUserName = s.getUsername();
                    tempPassword = s.getPassword();
                    System.out.println("---IN");
                    System.out.println("tempUserName: " + tempUserName);
                    System.out.println("tempPassword: " + tempUserName);
                    System.out.println("---EN");
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    public boolean serverStudentChangePassword(User user) throws IOException, ClassNotFoundException {
        System.out.println("---");
        System.out.println("tempUserName: " + tempUserName);
        System.out.println("tempPassword: " + tempUserName);
        System.out.println("---");
        // If no curr student login data, cannot change pwd. Return false
        if (tempUserName == null || tempPassword == null) return false;

        ArrayList<Student> db = StudentDB.getDb();
        for (Student s : db) {
            if (s.getUsername().equals(tempUserName)) {
                if (s.getPassword().equals(tempPassword)) {
                    Student newInfo = new Student(s.getId(), s.getName(), s.getAge(), s.getUsername(), user.getPassword());
                    if (StudentDB.modifyStudent(s, newInfo)) {
                        StudentDB.quitSave();
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
