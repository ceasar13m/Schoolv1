package com.ainur;

import com.ainur.models.Grade;
import com.ainur.models.Student;
import com.ainur.models.Subject;
import com.ainur.models.Teacher;

import java.sql.SQLException;
import java.util.ArrayList;

public interface Repository {
    public void addTeacher(Teacher teacher) throws SQLException;
    public void addStudent(Student student) throws SQLException;
    public void removeTeacher(int id) throws SQLException;
    public void removeStudent(int id) throws SQLException;
    public void addGrade(Grade grade) throws SQLException;
    public void addSubject(Subject subject) throws SQLException;
    public ArrayList<Student> getAllStudents();
    public ArrayList<Teacher> getAllTeachers();
}
