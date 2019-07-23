package com.ainur.servlets;

import com.ainur.MysqlRepositoryImpl;
import com.ainur.Repository;
import com.ainur.models.Grade;
import com.ainur.models.Grades;
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

public class GradeServlet extends HttpServlet {

    Repository repository = new MysqlRepositoryImpl();
    Gson gson = new Gson();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Grades grades = new Grades();
            grades.setArrayList(repository.getAllGrades());
            String jsonString = gson.toJson(grades, Grades.class);

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
            Grade grade = gson.fromJson(req.getReader(), Grade.class);

            boolean isCorrect = (grade.getName() != null) && (!grade.getName().isEmpty());
            if (isCorrect) {
                repository.addGrade(grade);
                resp.setStatus(HttpStatus.OK);
            }
            else
                resp.setStatus(HttpStatus.BAD_REQUEST);
        }

        catch (SQLException e) {
            resp.setStatus(HttpStatus.FORBIDDEN);
            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setMessage(e.getMessage());
            resp.getWriter().println(errorMessage.getMessage());
        }catch (Exception e) {
            resp.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setMessage(e.getMessage());
            resp.getWriter().println(errorMessage.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Grade grade = gson.fromJson(req.getReader(), Grade.class);
            boolean isCorrect = (grade.getName() != null) &&
                    (!grade.getName().isEmpty() &&
                            (grade.getName() != null));

            if (isCorrect) {
                repository.modGrade(grade);
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
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if (req.getHeader("gradeId") != null) {
                repository.removeGrade(Integer.parseInt(req.getHeader("gradeId")));
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
