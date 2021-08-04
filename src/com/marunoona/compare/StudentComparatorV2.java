package com.marunoona.compare;

import com.google.common.collect.ComparisonChain;

import java.util.Comparator;

public class StudentComparatorV2 implements Comparator<Student> {

    private StudentComparatorV2(){}

    public static StudentComparatorV2 create() {
        return new StudentComparatorV2();
    }

    @Override
    public int compare(Student s1, Student s2) {
        return ComparisonChain.start()
                .compare(s1.getAge(),s2.getAge())
                .compare(s1.getGrade(), s2.getGrade())
                .result();
    }
}
