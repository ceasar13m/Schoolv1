package com.ainur;

import com.ainur.servlets.GradeServlet;
import com.ainur.servlets.StudentServlet;
import com.ainur.servlets.SubjectServlet;
import com.ainur.servlets.TeacherServlet;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHandler;

public class Main {
    public static void main(String [] args) {
        Server server = new Server();
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(8080);
        server.setConnectors(new Connector[] {connector});
        ServletHandler servletHandler = new ServletHandler();
        server.setHandler(servletHandler);

        servletHandler.addServletWithMapping(GradeServlet.class, "/grades");
        servletHandler.addServletWithMapping(StudentServlet.class, "/students");
        servletHandler.addServletWithMapping(SubjectServlet.class, "/subjects");
        servletHandler.addServletWithMapping(TeacherServlet.class, "/teachers");

        try {
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}