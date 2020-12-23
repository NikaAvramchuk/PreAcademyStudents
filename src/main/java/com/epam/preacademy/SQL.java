package com.epam.preacademy;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQL {
    private static Connection connection;
    private static Statement statement;

    public static Statement getStatement() {
        return statement;
    }

    public static void connect() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/PreAcademyStudents", "postgres", "academik1718");

            statement = connection.createStatement(); /// select - execute query; create, drop - execute; insertInto, delete, set - executeUpdated

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("There is a problem with connection");
        }
    }


    public static void disconnect() {
        try {
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.err.println("There is a problem with disconnection");
        }

    }

    public static List<PreAcademyStudent> downloadDataStudents()  {
        connect();
        List<PreAcademyStudent> preAcademyStudentsFromSQL = new ArrayList<>();

        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery("SELECT * FROM PREACADEMYSTUDENT;");
            while (resultSet.next()) {
                preAcademyStudentsFromSQL.add(new PreAcademyStudent(resultSet.getString(2),resultSet.getString(3),resultSet.getInt(4),resultSet.getInt(5),resultSet.getInt(6)));
            }
        }
        catch (SQLException e) {
            System.err.println("Can`t execute query");
        }

        finally {
            disconnect();
        }

        return preAcademyStudentsFromSQL;

    }
}

