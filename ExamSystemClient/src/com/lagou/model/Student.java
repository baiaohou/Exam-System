package com.lagou.model;

import java.io.Serializable;

/**
 * @author baiaohou
 * @create 2020-07-12 12:35 AM
 */
public class Student implements Serializable {
    // attributes
    String id;
    String name;
    int age;
    String username;
    String password;
    private static final long serialVersionUID = 2339479448674976740L;

    // empty constructor
    public Student() {
    }

    // constructor
    public Student(String id, String name, int age, String username, String password) {
        setId(id);
        setName(name);
        setAge(age);
        setUsername(username);
        setPassword(password);
    }



    /*
        Getter and Setters.
     */
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (age != student.age) return false;
        if (id != null ? !id.equals(student.id) : student.id != null) return false;
        if (name != null ? !name.equals(student.name) : student.name != null) return false;
        if (username != null ? !username.equals(student.username) : student.username != null) return false;
        return password != null ? password.equals(student.password) : student.password == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + age;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

//
//public class Student implements Serializable {
//    // attributes
//    String id;
//    String name;
//    int age;
//    private static final long serialVersionUID = 2339479448674976740L;
//
//    // empty constructor
//    public Student() {
//    }
//
//    // constructor
//    public Student(String id, String name, int age) {
//        setId(id);
//        setName(name);
//        setAge(age);
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Student student = (Student) o;
//
//        if (age != student.age) return false;
//        if (id != null ? !id.equals(student.id) : student.id != null) return false;
//        return name != null ? name.equals(student.name) : student.name == null;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = id != null ? id.hashCode() : 0;
//        result = 31 * result + (name != null ? name.hashCode() : 0);
//        result = 31 * result + age;
//        return result;
//    }
//
//    /*
//        Getter and Setters.
//     */
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public int getAge() {
//        return age;
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }
//
//    @Override
//    public String toString() {
//        return "Student{" +
//                "id='" + id + '\'' +
//                ", name='" + name + '\'' +
//                ", age=" + age +
//                '}';
//    }
//}
