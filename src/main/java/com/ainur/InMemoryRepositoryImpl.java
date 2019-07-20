package com.ainur;

import com.ainur.models.Grade;
import com.ainur.models.Student;
import com.ainur.models.Subject;
import com.ainur.models.Teacher;
import com.ainur.util.NotFoundException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryRepositoryImpl implements Repository {


    private ConcurrentHashMap<Grade, Integer> gradesStorage = new ConcurrentHashMap<>();
    private ConcurrentHashMap<Student, Integer> studentsStorage = new ConcurrentHashMap<>();
    private ConcurrentHashMap<Teacher, Integer> teachersStorage = new ConcurrentHashMap<>();
    private ConcurrentHashMap<Subject, Integer> subjectsStorage = new ConcurrentHashMap<>();



    @Override
    public void addTeacher(Teacher teacher) throws SQLException {

    }

    @Override
    public void addStudent(Student student) throws SQLException {

    }

    @Override
    public void removeTeacher(int teacherId) throws SQLException, NotFoundException {

    }

    @Override
    public void removeStudent(int studentId) throws SQLException, Exception, NotFoundException {

    }

    @Override
    public void addGrade(Grade grade) throws SQLException {

    }

    @Override
    public void addSubject(Subject subject) throws SQLException {

    }

    @Override
    public ArrayList<Student> getAllStudents() throws SQLException {
        return null;
    }

    @Override
    public ArrayList<Teacher> getAllTeachers() throws SQLException {
        return null;
    }

    @Override
    public ArrayList<Student> getStudents(int gradeId) throws Exception, NotFoundException {
        return null;
    }

    @Override
    public void assignSubjectToTeacher(int idTeacher, int idSubject) throws SQLException {

    }

    @Override
    public ArrayList<Grade> getAllGrades() throws SQLException {
        return null;
    }

    @Override
    public void modTeacher(Teacher teacher) throws SQLException {

    }

    @Override
    public void modStudent() throws SQLException {

    }

    @Override
    public void modGrade() throws SQLException {

    }

    @Override
    public void modSubject() throws SQLException {

    }

    @Override
    public ArrayList<Subject> getAllSubjects() throws SQLException {
        return null;
    }
}
