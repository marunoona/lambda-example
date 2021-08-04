package com.marunoona.compare;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class StudentComparatorV1 {

    public List<Student> studentList = new ArrayList<>();

    public StudentComparatorV1() {
        studentList.add(new Student("A", 13, 2));
        studentList.add(new Student("B", 11, 5));
        studentList.add(new Student("C", 11, 1));
        studentList.add(new Student("D", 10, 3));
        studentList.add(new Student("E", 12, 4));
    }

    /**
     * 오름차순
     * @return
     */
    public List<Student> sortStudentByAscending(){
        //나이 순으로 정렬
        Comparator<Student> compareAge = Comparator.comparing(Student::getAge);
        //성적 순으로 정렬
        Comparator<Student> compareGrade = Comparator.comparing(Student::getGrade);
        //최종 정렬 순서 나이순->성적순
        Comparator<Student> studentComparator = compareAge.thenComparing(compareGrade);

        studentList.sort(studentComparator);

        for (Student student : studentList) {
            System.out.println("student: " + student);
        }

        return studentList;
    }

    /**
     * 내림차순
     * @return
     */
    public List<Student> sortStudentByDescending(){
        //나이 순으로 정렬
        Comparator<Student> compareAge = Comparator.comparing(Student::getAge).reversed();
        //성적 순으로 정렬
        Comparator<Student> compareGrade = Comparator.comparing(Student::getGrade).reversed();
        //최종 정렬 순서 나이순->성적순
        Comparator<Student> studentComparator = compareAge.thenComparing(compareGrade);

        studentList.sort(studentComparator);

        for (Student student : studentList) {
            System.out.println("student: " + student);
        }

        return studentList;
    }


}
