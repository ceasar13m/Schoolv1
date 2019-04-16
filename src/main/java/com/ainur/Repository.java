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
    public boolean removeTeacher(int id);
    public boolean removeStudent(int id);
    public boolean addGrade(Grade grade);
    public boolean addSubject(Subject subject);
    public ArrayList<Student> getAllStudents();
    public ArrayList<Teacher> getAllTeachers();
}
