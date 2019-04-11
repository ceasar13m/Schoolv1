package com.ainur;

import com.ainur.models.Grade;
import com.ainur.models.Student;
import com.ainur.models.Subject;
import com.ainur.models.Teacher;

import java.util.ArrayList;

public interface Repository {
    public boolean addTeacher(Teacher teacher);
    public boolean addStudent(Student student);
    public boolean addGrade(Grade grade);
    public boolean addSubject(Subject subject);
    public ArrayList<Student> getAllStudents();
    public ArrayList<Teacher> getAllTeachers();
}
