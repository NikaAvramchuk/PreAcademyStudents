package com.epam.preacademy;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        //uncomment it if you want to add another student to SQL
//        PreAcademyStudent preAcademyStudent = new PreAcademyStudent("Andrzej", "Wilk", 3,40,40);
//        PreAcademyStudent.registerStudent(preAcademyStudent);


        List<PreAcademyStudent> studentsFromSQL = SQL.downloadDataStudents();

        studentsFromSQL
                .stream()
                .sorted(PreAcademyStudent::compareTo)
                .forEach(System.out::println);

        System.out.println("-----------------------------------------------------");

        studentsFromSQL
                .stream()
                .sorted(PreAcademyStudent.SortByQuizzesASC)
                .forEach(System.out::println);

        System.out.println("-----------------------------------------------------");

        studentsFromSQL
                .stream()
                .sorted(PreAcademyStudent.SortByActivityASC)
                .forEach(System.out::println);

        System.out.println("-----------------------------------------------------");

        studentsFromSQL
                .stream()
                .sorted(PreAcademyStudent.SortByTasksDESC)
                .forEach(System.out::println);

    }


}

