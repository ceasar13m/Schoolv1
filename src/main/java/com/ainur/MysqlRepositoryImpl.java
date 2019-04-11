package com.ainur;

import com.ainur.models.Grade;
import com.ainur.models.Student;
import com.ainur.models.Subject;
import com.ainur.models.Teacher;

import java.sql.*;
import java.util.ArrayList;

public class MysqlRepositoryImpl implements Repository {

    private static final String  URL = "jdbc:mysql://localhost:3306"+
            "?verifyServerCertificate=false"+
            "&useSSL=false"+
            "&requireSSL=false"+
            "&useLegacyDatetimeCode=false"+
            "&amp"+
            "&serverTimezone=UTC";;
    private static final String USERNAME = "root";
    private static final String PASSWORD = "kazan13m";
    private static Connection connection;



    private static final MysqlRepositoryImpl repository = new MysqlRepositoryImpl();

    /************************************************************************************************************
     * Конструктор Singleton класса
     * Создается БД school
     * В БД создаются четыре таблицы:
     * 1. grades
     * 2. students
     * 3. subjects
     * 4. teachers
     */

    private MysqlRepositoryImpl() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            Statement statement = connection.createStatement();

            statement.executeUpdate("create database IF NOT EXISTS school;");
            statement.executeUpdate("use school;");
            statement.executeUpdate(
                    "CREATE TABLE if not exists grades (" +
                    "    id MEDIUMINT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                    "    name TEXT NOT NULL\n" +
                    ");");
            statement.executeUpdate(
                    "CREATE TABLE if not exists students (" +
                    "    id MEDIUMINT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                    "    firstName TEXT," +
                    "    secondName TEXT," +
                    "    gradeId mediumint," +
                    "    FOREIGN KEY (gradeId) REFERENCES grades(id)" +
                    ");");
            statement.executeUpdate(
                    "CREATE TABLE if not exists subjects (" +
                    "    id MEDIUMINT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                    "    name TEXT NOT NULL\n" +
                    ");");
            statement.executeUpdate(
                    "CREATE TABLE if not exists teachers (" +
                    "    id MEDIUMINT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                    "    firstName TEXT," +
                    "    secondName TEXT," +
                    "    subjectId mediumint," +
                    "    FOREIGN KEY (subjectId) REFERENCES subjects(id)" +
                    ");");


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /************************************************************************************************************
     * Singleton
     *
     *
     *
     *
     * @return
     */
    public static MysqlRepositoryImpl getInstance() {
        return repository;
    }


    /************************************************************************************************************
     * Добавление в таблицу grades нового класса
     * @param grade
     * @return
     */


    @Override
    public boolean addGrade(Grade grade) {
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.executeUpdate("use school");

            String tempString = "select * from grades where name = '" + grade.getName() + "';";
            ResultSet resultSet = statement.executeQuery(tempString);

            if(resultSet.next()) {
                return false;
            }
            else {
                String teacherInsertString = "insert into grades (name) values ('" + grade.getName() + "');";
                statement.executeUpdate(teacherInsertString);
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    /************************************************************************************************************
     * Добавление в таблицу subjects нового предмета
     * @param subject
     * @return
     */

    @Override
    public boolean addSubject(Subject subject) {
        return false;
    }


    /************************************************************************************************************
     * Добавление в таблицу teachers нового учителя
     * @param teacher
     * @return
     */
    @Override
    public boolean addTeacher(Teacher teacher) {
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.executeUpdate("use school");

            String tempString = "select * from teachers where firstName = '" + teacher.getFirstName() + "', secondName = '" + teacher.getSecondName() + "');";
            ResultSet resultSet = statement.executeQuery(tempString);

            if(resultSet.next()) {
                return false;
            }
            else {
                String teacherInsertString = "insert into teachers (firstName, secondName, subjectId) values ('" + teacher.getFirstName() + "','" + teacher.getSecondName() + "'," + 1 + ");";
                statement.executeUpdate(teacherInsertString);
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    /************************************************************************************************************
     * Добавление в таблицу students нового студента
     * @param student
     * @return
     */
    @Override
    public boolean addStudent(Student student) {
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.executeUpdate("use school");

            String tempString = "select * from students where firstName = '" + student.getFirstName() + "', secondName = '" + student.getSecondName() + "');";
            ResultSet resultSet = statement.executeQuery(tempString);

            if(resultSet.next()) {
                return false;
            }
            else {
                String studentInsertString = "insert into teachers (firstName, secondName, subjectId) values ('" + student.getFirstName() + "','" + student.getSecondName() + "'," + 1 + ");";
                statement.executeUpdate(studentInsertString);
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ArrayList<Student> getAllStudents() {
        return null;
    }

    @Override
    public ArrayList<Teacher> getAllTeachers() {
        return null;
    }

}
