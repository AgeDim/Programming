package servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ControllerServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        if (request.getParameter("xval") != null && request.getParameter("yval") != null &&
                request.getParameter("rval") != null && request.getParameter("timezone") != null) {
            requestDispatcher = request.getRequestDispatcher("/hit");
        } else {
            requestDispatcher = request.getRequestDispatcher("/index.jsp");
        }
        requestDispatcher.forward(request, response);
    }

}
