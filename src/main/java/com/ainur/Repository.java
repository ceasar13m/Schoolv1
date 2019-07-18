package com.ainur;

import com.ainur.models.Grade;
import com.ainur.models.Student;
import com.ainur.models.Subject;
import com.ainur.models.Teacher;
import com.ainur.util.NotFoundException;

import java.sql.SQLException;
import java.util.ArrayList;

public interface Repository {
    public void addTeacher(Teacher teacher) throws SQLException;
    public void addStudent(Student student) throws SQLException;
    public void removeTeacher(int teacherId) throws SQLException, NotFoundException;
    public void removeStudent(int studentId) throws SQLException, Exception, NotFoundException;
    public void addGrade(Grade grade) throws SQLException;
    public void addSubject(Subject subject) throws SQLException;
    public ArrayList<Student> getAllStudents() throws SQLException;
    public ArrayList<Teacher> getAllTeachers() throws SQLException;
    public ArrayList<Student> getStudents(int gradeId) throws Exception, NotFoundException;
    public void assignSubjectToTeacher(int idTeacher, int idSubject) throws SQLException;
    public ArrayList<Grade> getAllGrades() throws SQLException;
    public ArrayList<Subject> getAllSubjects() throws SQLException;
}
