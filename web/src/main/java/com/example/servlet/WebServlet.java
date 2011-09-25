package com.example.servlet;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.App;

public class WebServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        App app = new App();
        try {
            resp.getWriter().println(Arrays.asList(app.namesFromDocumentBuilder()));
            resp.getWriter().println(Arrays.asList(app.namesFromNokogiri()));
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    
}
