package com.ainur.servlets;

import com.ainur.MysqlRepositoryImpl;
import com.ainur.Repository;
import com.ainur.models.Teacher;
import com.ainur.models.Teachers;
import com.ainur.util.ErrorMessage;
import com.ainur.util.HttpStatus;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class TeacherServlet extends HttpServlet {

    Repository repository = new MysqlRepositoryImpl();
    Gson gson = new Gson();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            Teachers teachers = new Teachers();
            teachers.setArrayList(repository.getAllTeachers());
            String jsonString = gson.toJson(teachers, Teachers.class);


            resp.setContentType("application/json");
            resp.addHeader("Access-Control-Allow-Origin", "*");
            resp.setStatus(HttpStatus.OK);

            resp.getWriter().println(jsonString);
            resp.getWriter().flush();


        } catch (Exception e) {
            e.printStackTrace();
            PrintWriter writer = resp.getWriter();
            writer.println(e.fillInStackTrace());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            System.out.println(req.getReader());
            Teacher teacher = gson.fromJson(req.getReader(), Teacher.class);
            repository.addTeacher(teacher);
            resp.setStatus(HttpStatus.OK);

        } catch (Exception e) {
            resp.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setMessage(e.getMessage());
            resp.getWriter().println(gson.toJson(errorMessage, ErrorMessage.class));
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            repository.removeTeacher(id);
        } catch (SQLException e) {
            resp.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setMessage(e.getMessage());
            resp.getWriter().println(gson.toJson(errorMessage, ErrorMessage.class));
        }
    }
}
