package com.lagou.studentDB;

import com.lagou.model.Student;

import java.util.Scanner;

/**
 * Providing some controller methods.
 * @author baiaohou
 * @create 2020-07-12 1:10 AM
 */
public class Controller {

    /**
     * Get user request by asking them to type a number.
     * Return 1 for add, return 2 for delete, return 3 for modify, return 4 for search, 5 for iterate, 6 to quit.
     *
     * @return the request int
     */
    public static int getRequest() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Press 1 to add, 2 to delete, 3 to modify, 4 to search, 5 to iterate, 6 to quit.");
            String s = sc.next();
            // trim the string, then see if it's b/t 1-4
            if ((s.trim()).matches("[1-6]")) {
                return Integer.parseInt(s);
            }
            System.out.println("Invalid input. Please try again!");
        }
    }

    /**
     * Asks caller to input a student info: id, name, age
     * @return the student instance / object
     */
    public static Student getStudentInput() throws StudentIDException, StudentNameException {
        Scanner sc = new Scanner(System.in);
        String id, name;
        int age = 0;

        System.out.println("Enter student id (4-digit number): ");
        id = sc.next();
        if (!id.matches("\\d{4}")) {
            // Invalid input
            throw new StudentIDException("Invalid ID!");
        }


        System.out.println("Enter student name: ");
        name = sc.next();
        if (!name.matches("[A-Za-z]+")) {
            // Invalid input
            throw new StudentNameException("Invalid name!");
        }


        while (true) {
            System.out.println("Enter student age (0-99): ");
            age = sc.nextInt();
            if (age >= 0 && age <= 99) break;
            // Invalid input
            System.out.println("Age should between 0 and 99. Try again!");
        }
        return new Student(id, name, age, null, null);
    }


}
