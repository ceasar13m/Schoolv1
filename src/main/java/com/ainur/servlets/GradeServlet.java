package com.ainur.servlets;

import com.ainur.MysqlRepositoryImpl;
import com.ainur.Repository;
import com.ainur.models.Grade;
import com.ainur.util.ErrorMessage;
import com.ainur.util.HttpStatus;
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
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if (req.getParameterMap().containsKey("name") && req.getParameter("name") != null && !req.getParameter("name").isEmpty()) {
                Grade grade = new Grade();
                grade.setName(req.getParameter("name"));
                repository.addGrade(grade);

            } else {
                resp.setStatus(HttpStatus.BAD_REQUEST);
            }

        } catch (SQLException e) {
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
        super.doDelete(req, resp);
    }
}
