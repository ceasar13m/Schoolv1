package com.ainur.servlets;

import com.ainur.MysqlRepositoryImpl;
import com.ainur.Repository;
import com.ainur.models.Student;
import com.ainur.models.Students;
import com.ainur.util.ErrorMessage;
import com.ainur.util.HttpStatus;
import com.ainur.util.NotFoundException;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class StudentServlet extends HttpServlet {

    Repository repository = new MysqlRepositoryImpl();
    Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Students students = new Students();
            if (req.getParameterMap().containsKey("getAll") && req.getParameter("getAll").equals("true")) {
                students.setArrayList(repository.getAllStudents());
            } else {
                if (req.getParameterMap().containsKey("gradeId") && !req.getParameter("gradeId").isEmpty()) {
                    students.setArrayList(repository.getStudents(Integer.parseInt(req.getParameter("gradeId"))));
                }
            }
            String jsonString = gson.toJson(students, Students.class);
            resp.setContentType("application/json");
            resp.addHeader("Access-Control-Allow-Origin", "*");
            resp.setStatus(HttpStatus.OK);

            resp.getWriter().println(jsonString);
            resp.getWriter().flush();


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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            Student student = gson.fromJson(req.getReader(), Student.class);
            boolean isCorrect = (student.getFirstName() != null) &&
                            (!student.getFirstName().isEmpty() &&
                            (student.getSecondName() != null) &&
                            (!student.getSecondName().isEmpty()));


            if(isCorrect) {
                repository.addStudent(student);
                resp.setStatus(HttpStatus.OK);
            }
            else
                resp.setStatus(HttpStatus.BAD_REQUEST);

        } catch (SQLException e) {
            resp.setStatus(HttpStatus.FORBIDDEN);
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

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if (req.getParameterMap().containsKey("studentId") && !req.getParameter("studentId").equals("")) {
                repository.removeStudent(Integer.parseInt(req.getParameter("studentId")));
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
