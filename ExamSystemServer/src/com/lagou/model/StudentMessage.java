package com.lagou.model;

/**
 * @author baiaohou
 * @create 2020-07-29 7:25 PM
 */
public class StudentMessage implements java.io.Serializable {
    private static final long serialVersionUID = 1107852056473928357L;

    private String type; // 消息类型代表具体业务
    private Student student;

    public StudentMessage() {
    }

    public StudentMessage(String type, Student student) {
        this.type = type;
        this.student = student;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "StudentMessage{" +
                "type='" + type + '\'' +
                ", student=" + student +
                '}';
    }
}
