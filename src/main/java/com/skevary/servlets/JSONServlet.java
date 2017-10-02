package com.skevary.servlets;

import com.google.gson.Gson;
import com.skevary.service.DataService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/pages/secured/getFilteredData")
public class JSONServlet extends HttpServlet{
    private static final long serialVersionUID = 4520783334671208245L;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();

        DataService service = (DataService) getServletContext().getAttribute("dataService");
        String jsonData = "{ \"data\": " + new Gson().toJson(service.getFilteredData()) + "}";
        out.print(jsonData);
        out.flush();
    }

}
