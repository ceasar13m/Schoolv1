package com.ainur.servlets;

import com.ainur.MysqlRepositoryImpl;
import com.ainur.Repository;
import com.ainur.models.Teacher;
import com.ainur.models.Teachers;
import com.ainur.util.ErrorMessage;
import com.ainur.util.HttpStatus;
import com.ainur.util.NotFoundException;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Console;
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
            resp.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setMessage(e.getMessage());
            resp.getWriter().println(errorMessage.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            Teacher teacher = gson.fromJson(req.getReader(), Teacher.class);
            boolean isCorrect = (teacher.getFirstName() != null) &&
                    (!teacher.getFirstName().isEmpty() &&
                            (teacher.getSecondName() != null) &&
                            (!teacher.getSecondName().isEmpty()));

            if (isCorrect) {
                repository.addTeacher(teacher);
                resp.setStatus(HttpStatus.OK);
                resp.addHeader("Access-Control-Allow-Origin", "*");
            } else {
                resp.setStatus(HttpStatus.BAD_REQUEST);
                resp.addHeader("Access-Control-Allow-Origin", "*");
            }

        } catch (SQLException e) {
            resp.setStatus(HttpStatus.FORBIDDEN);
            resp.addHeader("Access-Control-Allow-Origin", "*");
            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setMessage(e.getMessage());
            resp.getWriter().println(errorMessage.getMessage());
        } catch (Exception e) {
            resp.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            resp.addHeader("Access-Control-Allow-Origin", "*");
            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setMessage(e.getMessage());
            resp.getWriter().println(errorMessage.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            boolean isCorrect = (req.getParameterMap().containsKey("teacherId")
                    && (!req.getParameter("teacherId").isEmpty())
                    && (req.getParameterMap().containsKey("subjectId")
                    && !req.getParameter("subjectId").isEmpty()));
            if (isCorrect) {
                int teacherId = Integer.parseInt(req.getParameter("teacherId"));
                int subjectId = Integer.parseInt(req.getParameter("subjectId"));

                repository.assignSubjectToTeacher(teacherId, subjectId);
                resp.setStatus(HttpStatus.OK);
            } else
                resp.setStatus(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            resp.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setMessage(e.getMessage());
            resp.getWriter().println(errorMessage.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if (req.getParameterMap().containsKey("teacherId") && !req.getParameter("teacherId").isEmpty()) {
                repository.removeTeacher(Integer.parseInt(req.getParameter("teacherId")));
                resp.setStatus(HttpStatus.OK);
            } else {
                resp.setStatus(HttpStatus.BAD_REQUEST);
            }
        } catch (NotFoundException e) {
            resp.setStatus(HttpStatus.NOT_FOUND);
            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setMessage(e.getMessage());
            resp.getWriter().println(errorMessage.getMessage());

        } catch (NumberFormatException e) {
            resp.setStatus(HttpStatus.BAD_REQUEST);
            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setMessage(e.getMessage());
            resp.getWriter().println(errorMessage.getMessage());

        } catch (Exception e) {
            resp.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setMessage(e.getMessage());
            resp.getWriter().println(errorMessage.getMessage());
        }
    }
}
