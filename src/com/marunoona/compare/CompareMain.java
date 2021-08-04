package com.marunoona.compare;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CompareMain {

    public static void main(String[] args) {

        List<Student> studentsA = new StudentComparatorV1().sortStudentByAscending();
        System.out.println();
        List<Student> studentsB = new StudentComparatorV1().sortStudentByDescending();

        System.out.println();

        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student("A", 13, 2));
        studentList.add(new Student("B", 11, 5));
        studentList.add(new Student("C", 11, 1));
        studentList.add(new Student("D", 10, 3));
        studentList.add(new Student("E", 12, 4));
        Collections.sort(studentList, StudentComparatorV2.create());

        for (Student student : studentList) {
            System.out.println("student = " + student);
        }
    }
}
