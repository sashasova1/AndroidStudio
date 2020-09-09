package com.chnulabs.students;

import java.util.ArrayList;
import java.util.Arrays;

public class Student {
    private String name;
    private String groupNumber;

    public Student(String name, String groupNumber) {
        this.name = name;
        this.groupNumber = groupNumber;
    }

    public String getName() {
        return name;
    }
    public String getGroupNumber() {
        return groupNumber;
    }

    private final static ArrayList<Student> students = new ArrayList<Student>(
            Arrays.asList(
                    new Student("Вовчик Владимир","301"),
                    new Student("Олек Жевтобрюхов","301"),
                    new Student("Макс Дед","301"),
                    new Student("Александр Саша","302"),
                    new Student("Кирилл Удачин","302"),
                    new Student("Джефф","302"),
                    new Student("Саня Невский","303"),
                    new Student("Вальдемар Ревво","303"),
                    new Student("Костянтин Блондин","303")
            )
    );
    public static ArrayList<Student> getStudents(String groupNumber){
        ArrayList<Student> stList = new ArrayList<>();
        for(Student s: students) {
            if (s.getGroupNumber().equals(groupNumber)){
                stList.add(s);
            }
        }
        return stList;
    }
}
