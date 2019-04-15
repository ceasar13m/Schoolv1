package com.ainur.servlets;

import com.ainur.MysqlRepositoryImpl;
import com.ainur.Repository;
import com.ainur.models.Student;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class StudentServlet extends HttpServlet
{

    Repository repository = new MysqlRepositoryImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Student student = new Student();
        student.setFirstName(req.getParameter("firstName"));
        student.setSecondName(req.getParameter("secondName"));
        student.setGradeId(Integer.parseInt(req.getParameter("gradeId")));

        repository.addStudent(student);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Student student = new Student();
        student.setFirstName(req.getParameter("firstName"));
        student.setSecondName(req.getParameter("secondName"));
        student.setGradeId(Integer.parseInt(req.getParameter("gradeId")));
        repository.removeStudent(student);
    }
}
