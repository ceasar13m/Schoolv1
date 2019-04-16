package com.ainur;

import com.ainur.models.Grade;
import com.ainur.models.Student;
import com.ainur.models.Subject;
import com.ainur.models.Teacher;

import java.sql.*;
import java.util.ArrayList;

public class MysqlRepositoryImpl implements Repository {

    private static final String URL = "jdbc:mysql://localhost:3306" +
            "?verifyServerCertificate=false" +
            "&useSSL=false" +
            "&requireSSL=false" +
            "&useLegacyDatetimeCode=false" +
            "&amp" +
            "&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "kazan13m";
    private static Connection connection;


    /************************************************************************************************************
     * Конструктор Singleton класса
     * Создается БД school
     * В БД создаются пять:
     * 1. grades
     * 2. students
     * 3. subjects
     * 4. teachers
     * 5. teacherssubjects // какой учитель какие предметы ведет // teacherId - subjectId
     */

    public MysqlRepositoryImpl() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            Statement statement = connection.createStatement();

            statement.executeUpdate("create database IF NOT EXISTS school;");
            statement.executeUpdate("use school;");
            statement.executeUpdate(
                    "CREATE TABLE if not exists grades (" +
                            "    id int AUTO_INCREMENT not null," +
                            "    name TEXT NOT NULL," +
                            "    primary key (id)" +
                            ");");
            statement.executeUpdate(
                    "CREATE TABLE if not exists students (" +
                            "    id int AUTO_INCREMENT NOT NULL  PRIMARY KEY," +
                            "    firstName TEXT not null," +
                            "    secondName TEXT not null," +
                            "    gradeId int not null," +
                            "    FOREIGN KEY (gradeId) REFERENCES grades(id)" +
                            ");");
            statement.executeUpdate(
                    "CREATE TABLE if not exists subjects (" +
                            "    id int AUTO_INCREMENT NOT NULL  PRIMARY KEY," +
                            "    name TEXT NOT NULL\n" +
                            ");");
            statement.executeUpdate(
                    "CREATE TABLE if not exists teachers (" +
                            "    id int AUTO_INCREMENT NOT NULL  PRIMARY KEY," +
                            "    firstName TEXT not null," +
                            "    secondName TEXT not null" +
                            ");");

            statement.executeUpdate(
                    "CREATE TABLE if not exists teachersSubjects (" +
                            "    teacherId int not null," +
                            "    subjectId int not null," +
                            "    FOREIGN KEY (teacherId) REFERENCES teachers(id), " +
                            "    FOREIGN KEY (subjectId) REFERENCES subjects(id) " +
                            ");");


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /************************************************************************************************************
     *  Добавление в таблицу grades нового класса
     * @param grade
     * @throws SQLException
     */


    @Override
    public void addGrade(Grade grade) throws SQLException {
        Statement statement;
        statement = connection.createStatement();
        statement.executeUpdate("use school");
        String gradeInsertString = "insert into grades set name = '" + grade.getName() + "';";
        statement.executeUpdate(gradeInsertString);

    }


    /************************************************************************************************************
     *  Добавление в таблицу subjects нового предмета
     * @param subject
     * @throws SQLException
     */

    @Override
    public void addSubject(Subject subject) throws SQLException {
        Statement statement;

        statement = connection.createStatement();
        statement.executeUpdate("use school");

        String subjectInsertString = "insert into subjects set name = '" + subject.getName() + "';";
        statement.executeUpdate(subjectInsertString);

    }


    /************************************************************************************************************
     *  Добавление в таблицу teachers нового учителя
     * @param teacher
     * @throws SQLException
     */
    @Override
    public void addTeacher(Teacher teacher) throws SQLException {
        Statement statement;
        statement = connection.createStatement();

        String teacherInsertString = "insert into teachers (firstName, secondName) values ('" +
                teacher.getFirstName() + "','" +
                teacher.getSecondName() + "');";
        statement.executeUpdate(teacherInsertString);

    }


    /************************************************************************************************************
     * Удаление учителя
     * @param id
     * @throws SQLException
     */
    @Override
    public void removeTeacher(int id) throws SQLException {

            Statement statement = connection.createStatement();
            statement.executeUpdate("use school");
            String removeTeacherString = "delete from teachers where id = " + id;
            statement.executeUpdate(removeTeacherString);

    }


    /************************************************************************************************************


    /**
     *  Добавление в таблицу students нового студента
     * @param student
     * @throws SQLException
     */
    @Override
    public void addStudent(Student student) throws SQLException {
        Statement statement;
        statement = connection.createStatement();
        statement.executeUpdate("use school");


        String studentInsertString = "insert into students (firstName, secondName, gradeId) " + "values ('" +
                student.getFirstName() + "','" +
                student.getSecondName() + "'," +
                student.getGradeId() + ");";

        statement.executeUpdate(studentInsertString);
    }


    /************************************************************************************************************
     * Удаление студента
     * @param id
     * @throws SQLException
     */
    @Override
    public void removeStudent(int id) throws SQLException {
            Statement statement = connection.createStatement();
            statement.executeUpdate("use school");
            String removeStudentString = "delete from students where id =" + id;
            statement.executeUpdate(removeStudentString);

    }



    @Override
    public ArrayList<Student> getAllStudents() {
        ArrayList<Student> students = new ArrayList<>();

        Statement statement;
        try {
            statement = connection.createStatement();
            statement.executeUpdate("use school");
            String tempString = "select * from students";
            ResultSet resultSet = statement.executeQuery(tempString);
            while(resultSet.next()) {
                Student student = new Student();
                student.setFirstName(resultSet.getString(2));
                student.setSecondName(resultSet.getString(3));
                student.setGradeId(Integer.parseInt(resultSet.getString(4)));
                students.add(student);
            }

            return students;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<Teacher> getAllTeachers() {
        ArrayList<Teacher> teachers = new ArrayList<>();

        Statement statement;
        try {
            statement = connection.createStatement();
            statement.executeUpdate("use school");
            String tempString = "select * from teachers";
            ResultSet resultSet = statement.executeQuery(tempString);
            while(resultSet.next()) {
                Teacher teacher = new Teacher();
                teacher.setFirstName(resultSet.getString(2));
                teacher.setSecondName(resultSet.getString(3));
                teachers.add(teacher);
            }
            return teachers;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
