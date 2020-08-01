package com.lagou.studentDB;

import com.lagou.model.Student;

import java.io.*;
import java.util.ArrayList;

/**
 * @author baiaohou
 * @create 2020-07-12 12:43 AM
 */
public class StudentDB implements Serializable {
    /**
     * The student database, including all student info.
     */
    // static attribute: array list of students
    public static ArrayList<Student> db = new ArrayList<>();
    private static final long serialVersionUID = -1182575366452307756L;

    public static ArrayList<Student> getDb() throws IOException, ClassNotFoundException {
        iterateShow();
        return db;
    }

    /**
     * Add a student
     * @param student to be added
     */
    public static boolean addStudent(Student student) throws IOException, ClassNotFoundException {
        return db.add(student); // add success -> return true

    }

    /**
     * Delete a student
     * @param student to be deleted
     */
    public static boolean deleteStudent(Student student) {
        return db.remove(student); // delete success -> return true
    }

    /**
     * Modify the info of a student
     * @param oldInfo of the studentUser
     * @param newInfo of the student
     */
    public static boolean modifyStudent(Student oldInfo, Student newInfo) {
        if (!db.contains(oldInfo)) return false;
        int index = db.indexOf(oldInfo);
        db.remove(oldInfo);
        db.add(index, newInfo);
        return true;
    }

    /**
     * Justify if a student exists in db
     * @param student to be searched for
     * @return true if exists
     */
    public static boolean searchStudent(Student student) {
        return db.contains(student);
    }

    /**
     * Show the current db info
     */
    public static String iterateShow() throws IOException, ClassNotFoundException {
        readList();
        String ret = "---------------------------- Student Info Database ----------------------------\n";
        ret += "   Student ID      Student Name      Student Age      Username      Password\n";
        ret += "-------------------------------------------------------------------------------\n";
        for (Student each : db) {
            ret += ("      " + each.getId() + "             " + each.getName()
                            + "               " + each.getAge() + "            "
                        + each.getUsername() + "        " + each.getPassword() + "\n");
        }
        ret += "-------------------------------------------------------------------------------";

        quitSave();
        return ret;
    }

    /**
     * Read file (object)
     */
    public static void readList() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("/Users/baiaohou/Desktop/workspace_idea/javase/ExamSystemServer/src/com/lagou/studentDB/data.txt"));
        db = (ArrayList<Student>) ois.readObject();
        ois.close();
    }

    /**
     * Save file (object)
     */
    public static void quitSave() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("/Users/baiaohou/Desktop/workspace_idea/javase/ExamSystemServer/src/com/lagou/studentDB/data.txt"));
        oos.writeObject(db);
        oos.close();
    }
}

