package com.ainur;

import com.ainur.models.Grade;
import com.ainur.models.Student;
import com.ainur.models.Subject;
import com.ainur.models.Teacher;
import com.ainur.util.NotFoundException;

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


    /**
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
                            "    name varchar (3) NOT NULL," +
                            "    primary key (id)" +
                            ");");
            statement.executeUpdate(
                    "CREATE TABLE if not exists students (" +
                            "    id int AUTO_INCREMENT NOT NULL  PRIMARY KEY," +
                            "    firstName varchar (30) not null," +
                            "    secondName TEXT not null," +
                            "    gradeId int not null," +
                            "    FOREIGN KEY (gradeId) REFERENCES grades(id)" +
                            ");");
            statement.executeUpdate(
                    "CREATE TABLE if not exists subjects (" +
                            "    id int AUTO_INCREMENT NOT NULL  PRIMARY KEY," +
                            "    name varchar(30) NOT NULL\n" +
                            ");");
            statement.executeUpdate(
                    "CREATE TABLE if not exists teachers (" +
                            "    id int AUTO_INCREMENT NOT NULL  PRIMARY KEY," +
                            "    firstName varchar (30) not null," +
                            "    secondName varchar (30) not null" +
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


    /**
     * Добавление в таблицу grades нового класса
     *
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


    /**
     * Добавление в таблицу subjects нового предмета
     *
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


    /**
     * Добавление в таблицу teachers нового учителя
     *
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

    @Override
    public void modTeacher(Teacher teacher) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("use school");
        String modString = "update teachers set firstName = '"+teacher.getFirstName()+"', secondName = '"+teacher.getSecondName()+"'  WHERE ID =" + teacher.getId();
        statement.executeUpdate(modString);
    }

    @Override
    public void modStudent(Student student) throws SQLException {
        System.out.println(student.getFirstName());
        System.out.println(student.getFirstName() + " " + student.getId());
        Statement statement = connection.createStatement();
        statement.executeUpdate("use school");
        String modString = "update students set firstName = '"+student.getFirstName()+ "', gradeId = '" + student.getGradeId() + "', secondName = '"+student.getSecondName()+"'  WHERE ID =" + student.getId();
        statement.executeUpdate(modString);
    }

    @Override
    public void modGrade(Grade grade) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("use school");
        String modString = "update grades set name = '"+grade.getName()+"'  WHERE ID =" + grade.getId();
        statement.executeUpdate(modString);
    }

    @Override
    public void modSubject(Subject subject) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("use school");
        String modString = "update subjects set name = '"+subject.getName()+"'  WHERE ID =" + subject.getId();
        statement.executeUpdate(modString);
    }

    /**
     * Удаление учителя
     *
     * @param teacherId
     * @throws SQLException
     */
    @Override
    public void removeTeacher(int teacherId) throws SQLException, NotFoundException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("use school");
        String tempString = "select * from teachers where id = " + teacherId;
        ResultSet resultSet = statement.executeQuery(tempString);
        if (!resultSet.next())
            throw new NotFoundException();

        String removeStudentString = "delete from teachers where id =" + teacherId;
        statement.executeUpdate(removeStudentString);

    }


    /**
     * Добавление в таблицу students нового студента
     *
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


    /**
     * Удаление студента
     *
     * @param studentId
     * @throws SQLException
     */
    @Override
    public void removeStudent(int studentId) throws SQLException, NotFoundException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("use school");
        String tempString = "select * from students where id = " + studentId;
        ResultSet resultSet = statement.executeQuery(tempString);
        if (!resultSet.next())
            throw new NotFoundException();

        String removeStudentString = "delete from students where id =" + studentId;
        statement.executeUpdate(removeStudentString);

    }


    /**
     * Получение списка студентов определенного класса
     *
     * @param gradeId
     * @return
     */
    @Override
    public ArrayList<Student> getStudents(int gradeId) throws Exception, NotFoundException {
        ArrayList<Student> students = new ArrayList<>();

        Statement statement;

        statement = connection.createStatement();
        statement.executeUpdate("use school");

        String tempString = "select * from students where gradeId = " + gradeId;
        ResultSet resultSet = statement.executeQuery(tempString);
        if (!resultSet.next())
            throw new NotFoundException();

        tempString = "select * from students where gradeId = " + gradeId;
        resultSet = statement.executeQuery(tempString);

        while (resultSet.next()) {
            Student student = new Student();
            student.setId(Integer.parseInt(resultSet.getString(1)));
            student.setFirstName(resultSet.getString(2));
            student.setSecondName(resultSet.getString(3));
            student.setGradeId(Integer.parseInt(resultSet.getString(4)));
            students.add(student);
        }

        return students;

    }


    /**
     * Получение списка всех студентов
     *
     * @return
     */
    @Override
    public ArrayList<Student> getAllStudents() throws SQLException {
        ArrayList<Student> students = new ArrayList<>();

        Statement statement;

        statement = connection.createStatement();
        statement.executeUpdate("use school");
        String tempString = "select * from students";
        ResultSet resultSet = statement.executeQuery(tempString);
        while (resultSet.next()) {
            Student student = new Student();
            student.setId(Integer.parseInt(resultSet.getString(1)));
            student.setFirstName(resultSet.getString(2));
            student.setSecondName(resultSet.getString(3));
            student.setGradeId(Integer.parseInt(resultSet.getString(4)));
            students.add(student);
        }

        return students;

    }


    /**
     * Получение списка всех классов
     *
     * @return
     * @throws SQLException
     */

    public ArrayList<Grade> getAllGrades() throws SQLException {
        ArrayList<Grade> grades = new ArrayList<>();

        Statement statement;

        statement = connection.createStatement();
        statement.executeUpdate("use school");
        String tempString = "select * from grades";
        ResultSet resultSet = statement.executeQuery(tempString);
        while (resultSet.next()) {
            Grade grade = new Grade();
            grade.setId(Integer.parseInt(resultSet.getString(1)));
            grade.setName(resultSet.getString(2));
            grades.add(grade);
        }

        return grades;

    }

    @Override
    public ArrayList<Subject> getAllSubjects() throws SQLException {
        ArrayList<Subject> subjects = new ArrayList<>();

        Statement statement;

        statement = connection.createStatement();
        statement.executeUpdate("use school");
        String tempString = "select * from subjects";
        ResultSet resultSet = statement.executeQuery(tempString);
        while (resultSet.next()) {
            Subject subject = new Subject();
            subject.setId(Integer.parseInt(resultSet.getString(1)));
            subject.setName(resultSet.getString(2));
            subjects.add(subject);
        }

        return subjects;
    }


    /**
     * Получение списка всех учителей
     *
     * @return
     */
    @Override
    public ArrayList<Teacher> getAllTeachers() throws SQLException {
        ArrayList<Teacher> teachers = new ArrayList<>();

        Statement statement;
        statement = connection.createStatement();
        statement.executeUpdate("use school");
        String tempString = "select * from teachers";

        ResultSet resultSet = statement.executeQuery(tempString);
        while (resultSet.next()) {
            Teacher teacher = new Teacher();
            teacher.setId(Integer.parseInt(resultSet.getString(1)));
            teacher.setFirstName(resultSet.getString(2));
            teacher.setSecondName(resultSet.getString(3));
            teachers.add(teacher);
        }
        return teachers;

    }

    @Override
    public void removeGrade(int gradeId) throws SQLException, NotFoundException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("use school");
        String tempString = "select * from grades where id = " + gradeId;
        ResultSet resultSet = statement.executeQuery(tempString);
        if (!resultSet.next())
            throw new NotFoundException();

        String removeStudentString = "delete from grades where id =" + gradeId;
        statement.executeUpdate(removeStudentString);
    }

    @Override
    public void removeSubject(int subjectId) throws SQLException, Exception, NotFoundException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("use school");
        String tempString = "select * from subjects where id = " + subjectId;
        ResultSet resultSet = statement.executeQuery(tempString);
        if (!resultSet.next())
            throw new NotFoundException();

        String removeStudentString = "delete from subjects where id =" + subjectId;
        statement.executeUpdate(removeStudentString);
    }

    @Override
    public void assignSubjectToTeacher(int idTeacher, int idSubject) throws SQLException {
        Statement statement;
        statement = connection.createStatement();
        statement.executeUpdate("use school");
        String InsertString = "insert into teacherssubjects (teacherId, subjectId) " + "values (" +
                idTeacher + "," +
                idSubject + ");";

        statement.executeUpdate(InsertString);
    }


}
