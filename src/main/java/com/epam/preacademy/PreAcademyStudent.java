package com.epam.preacademy;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class PreAcademyStudent implements Comparable<PreAcademyStudent> {
    private final String name;
    private final String surname;

    private int totalPoints;

    private int quizzesNumber;
    private int activityPoints;
    private int tasksPoints;

    public static final Comparator<PreAcademyStudent> SortByActivityASC = Comparator.comparingInt(o -> o.activityPoints);
    public static final Comparator<PreAcademyStudent> SortByQuizzesASC = Comparator.comparingInt(o -> o.quizzesNumber);
    public static final Comparator<PreAcademyStudent> SortByTasksASC = Comparator.comparingInt(o -> o.tasksPoints);

    public static final Comparator<PreAcademyStudent> SortByTasksDESC = (o1, o2) -> Integer.compare(o2.tasksPoints, o1.tasksPoints);
    public static final Comparator<PreAcademyStudent> SortByQuizzesDESC = (o1, o2) -> Integer.compare(o2.quizzesNumber, o1.quizzesNumber);
    public static final Comparator<PreAcademyStudent> SortByActivityDESC = (o1, o2) -> Integer.compare(o2.activityPoints, o1.activityPoints);



    public PreAcademyStudent(String name, String surname, int quizzesNumber, int activityPoints, int tasksPoints) {
        this.name = name;
        this.surname = surname;
        this.quizzesNumber = quizzesNumber;
        this.activityPoints = activityPoints;
        this.tasksPoints = tasksPoints;
        totalPoints=quizzesNumber+activityPoints+tasksPoints;
    }


    @Override
    public int compareTo(PreAcademyStudent o) {
        return Integer.compare(o.totalPoints, totalPoints);
    }

    @Override
    public String toString() {
        return name + " " + surname + ", total: " + totalPoints +
                ", activity: " + activityPoints + ", tasks:" +tasksPoints +
                ", quizz: " + quizzesNumber;
    }


//old method from previous task
    public static void sortList(List<PreAcademyStudent> list) {
        for(int i=0; i<list.size(); i++) {
            int smallest=i;
            for(int j=i+1; j<list.size(); j++) {
                if(list.get(smallest).compareTo(list.get(j))>0)
                    smallest=j;
            }
            PreAcademyStudent temp =list.get(i);
            list.set(i,list.get(smallest));
            list.set(smallest, temp);

        }
    }

    // method which add total points to SQL, not using now
    public static void addTotal (List <PreAcademyStudent> studentsFromSQL) {
        SQL.connect();
        try {
            for(PreAcademyStudent preAcademyStudent: studentsFromSQL) {
                SQL.getStatement().executeUpdate("UPDATE PREACADEMYSTUDENT SET TotalPoints = " + preAcademyStudent.totalPoints +
                        " WHERE Name = '" + preAcademyStudent.name + "' AND Surname = '" + preAcademyStudent.surname + "';");
            }
        } catch (SQLException e) {
            System.err.println("Can`t execute update: adding total points");
        }

        finally {
            SQL.disconnect();
        }

    }

    static void registerStudent (PreAcademyStudent preAcademyStudent)  {
        SQL.connect();
        try {
            SQL.getStatement().executeUpdate("INSERT INTO PREACADEMYSTUDENT " +
                    "(Name, Surname, QuizzesNumber,TasksPoints, ActivityPoints, TotalPoints) " +
                    "VALUES ('" +preAcademyStudent.name + "', '" + preAcademyStudent.surname +
                    "', " + preAcademyStudent.quizzesNumber + ", " + preAcademyStudent.tasksPoints +
                    ", " + preAcademyStudent.activityPoints + ", " + preAcademyStudent.totalPoints +");");
        } catch (SQLException e) {
            System.err.println("Can`t execute update: adding new student");
        }
    }

}


