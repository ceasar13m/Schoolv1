package com.ainur.servlets;

import com.ainur.MysqlRepositoryImpl;
import com.ainur.Repository;
import com.ainur.util.ErrorMessage;
import com.ainur.util.HttpStatus;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TeacherSubjectServlet extends HttpServlet {

    Repository repository = new MysqlRepositoryImpl();
    Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
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
        super.doDelete(req, resp);
    }
}
