//package com.lagou.questionDB;
//
//import com.lagou.model.Student;
//import com.lagou.studentDB.StudentIDException;
//import com.lagou.studentDB.StudentNameException;
//
//import java.io.IOException;
//
///**
// * Main entry of the program.
// * @author baiaohou
// * @create 2020-07-12 11:12 PM
// */
//public class Main {
//
//    public static void databaseEntry(String[] args) throws IOException, ClassNotFoundException {
//        System.out.println("Welcome to use the student database!");
//        try {
//            QuestionDB.readList();
//        } catch (IOException e) {
//            //e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        /**
//         * In this while loop, keep tracking what requests from the user,
//         * and call the methods.
//         * Caller call 1: add a student
//         * Caller call 2: delete a student
//         * Caller call 3: modify a student
//         * Caller call 4: search for a specific student
//         * Caller call 5: iterate the db to show all info
//         * Caller call 6: quit the program
//         */
//        label : while (true) {
//            switch (Controller.getRequest()) {
//                case 1: // add
//                    System.out.println("Student you want to add:");
//                    Student addStudent = null;
//                    try {
//                        addStudent = Controller.getStudentInput();
//                    } catch (StudentIDException e) {
//                        e.printStackTrace();
//                    } catch (StudentNameException e) {
//                        e.printStackTrace();
//                    }
//                    if (QuestionDB.addStudent(addStudent)) {
////                        System.out.println("Add success!");
//                    } else {
//                        System.out.println("Add failed! Try again!");
//                    }
//                    break;
//                case 2: // delete
//                    System.out.println("Student you want to delete:");
//                    Student delStudent = null;
//                    try {
//                        delStudent = Controller.getStudentInput();
//                    } catch (StudentIDException e) {
//                        e.printStackTrace();
//                    } catch (StudentNameException e) {
//                        e.printStackTrace();
//                    }
//                    if (QuestionDB.deleteStudent(delStudent)) {
////                        System.out.println("Delete success!");
//                    } else {
//                        System.out.println("Delete failed! No such student!");
//                    }
//                    break;
//                case 3: // modify
//                    System.out.println("Student you want to modify (old info):");
//                    Student old = null;
//                    try {
//                        old = Controller.getStudentInput();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                    if (!QuestionDB.searchStudent(old)) {
//                        System.out.println("No such student found in database! Modify failed!");
//                        break;
//                    }
//                    System.out.println("Now enter the new info of this student:");
//                    Student curr = null;
//                    try {
//                        curr = Controller.getStudentInput();
//                    } catch (StudentIDException e) {
//                        e.printStackTrace();
//                    } catch (StudentNameException e) {
//                        e.printStackTrace();
//                    }
//                    QuestionDB.modifyStudent(old, curr);
////                    System.out.println("Modify student success!");
//                    break;
//                case 4: // search
//                    System.out.println("Student you want to search for:");
//                    Student searchStudent = null;
//                    try {
//                        searchStudent = Controller.getStudentInput();
//                    } catch (StudentIDException e) {
//                        e.printStackTrace();
//                    } catch (StudentNameException e) {
//                        e.printStackTrace();
//                    }
//                    if (QuestionDB.searchStudent(searchStudent)) {
//                        System.out.println("Yay! This student is truly in database!");
//                    } else {
//                        System.out.println("Nah, no such student found in database!");
//                    }
//                    break;
//                case 5: // iterate
//                    System.out.println("Now iterate the database in order:");
//                    QuestionDB.iterateShow();
//                    break;
//                case 6: // quit
//                    // Save info / write file
//                    try {
//                        QuestionDB.quitSave();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    break label;
//            }
//        }
//        System.out.println("Bye-bye!");
//
//    }
//}
