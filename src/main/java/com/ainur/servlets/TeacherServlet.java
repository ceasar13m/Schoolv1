package com.ainur.servlets;

import com.ainur.MysqlRepositoryImpl;
import com.ainur.Repository;
import com.ainur.models.Teacher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TeacherServlet extends HttpServlet {

    Repository repository = new MysqlRepositoryImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Teacher teacher = new Teacher();
        teacher.setFirstName(req.getParameter("firstName"));
        teacher.setSecondName(req.getParameter("secondName"));


        repository.addTeacher(teacher);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Teacher teacher = new Teacher();
        teacher.setFirstName(req.getParameter("firstName"));
        teacher.setSecondName(req.getParameter("secondName"));
        repository.removeTeacher(teacher);
    }
}
