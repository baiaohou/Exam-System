package com.lagou.questionDB;

import com.lagou.model.Question;
import com.lagou.model.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author baiaohou
 * @create 2020-07-12 12:43 AM
 */
public class QuestionDB implements Serializable {
    private static final long serialVersionUID = 7200996717929553108L;
    /**
     * The question database, including all question info.
     */
    // static attribute: array list of Questions
    public static ArrayList<Question> db = new ArrayList<>();

    public static Map<String, Integer> scores = new HashMap<>();


    /**
     * Add a question
     * @param question to be added
     */
    public static boolean addQuestion(Question question) throws IOException, ClassNotFoundException {
        return db.add(question); // add success -> return true

    }

    /**
     * Delete a question
     * @param question to be deleted
     */
    public static boolean deleteQuestion(Question question) {
        return db.remove(question); // delete success -> return true
    }

    /**
     * Modify the info of a question
     * @param oldInfo of the question
     * @param newInfo of the question
     */
    public static boolean modifyQuestion(Question oldInfo, Question newInfo) {
        if (!db.contains(oldInfo)) return false;
        int index = db.indexOf(oldInfo);
        db.remove(oldInfo);
        db.add(index, newInfo);
        return true;
    }

    /**
     * Justify if a question exists in db
     */
    public static boolean searchQuestion(Question question) {
        return db.contains(question);
    }

    /**
     * Show the current db info
     */
    public static String iterateShow() throws IOException, ClassNotFoundException {
        readList();
        String ret = "---------------------------- Question Database ----------------------------\n";

        for (Question each : db) {
            ret += each.toString();
            ret += "----------------------------------------------------------------------------\n";
        }

        quitSave();
        return ret;
    }

    /**
     * Read file (object)
     */
    public static void readList() throws IOException, ClassNotFoundException {
        File tmpDir = new File("/Users/baiaohou/Desktop/workspace_idea/javase/ExamSystemServer/src/com/lagou/questionDB/data.txt");
        boolean exists = tmpDir.exists();
        if (!exists) return;
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("/Users/baiaohou/Desktop/workspace_idea/javase/ExamSystemServer/src/com/lagou/questionDB/data.txt"));
        db = (ArrayList<Question>) ois.readObject();
        ois.close();
    }

    /**
     * Save file (object)
     */
    public static void quitSave() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("/Users/baiaohou/Desktop/workspace_idea/javase/ExamSystemServer/src/com/lagou/questionDB/data.txt"));
        oos.writeObject(db);
        oos.close();
    }
}

