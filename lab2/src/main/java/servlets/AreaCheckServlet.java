package servlets;

import beans.Entry;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

public class AreaCheckServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        long startTime = System.nanoTime();
        double xValue = 0;
        double yValue = 0;
        double rValue = 0;
        boolean isHit;

        String xString = request.getParameter("xval");
        String yString = request.getParameter("yval").replace(',', '.');
        String rString = request.getParameter("rval");
        boolean isValuesValid = validateValues(xString, yString, rString);

        if (isValuesValid) {
            xValue = Double.parseDouble(xString);
            yValue = Double.parseDouble(yString);
            rValue = Double.parseDouble(rString);
            isHit = checkHit(xValue, yValue, rValue);
        } else {
            response.setStatus(422);
            return;
        }

        OffsetDateTime currentTimeObject = OffsetDateTime.now(ZoneOffset.UTC);
        String currentTime;
        currentTimeObject = currentTimeObject.minusMinutes(Long.parseLong(request.getParameter("timezone")));
        currentTime = currentTimeObject.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        String executionTime = String.valueOf((System.nanoTime() - startTime) * Math.pow(10, -9));
        ServletContext context = getServletContext();
        Object rawHistory = context.getAttribute("history");
        LinkedList<Entry> history;
        if (rawHistory != null) {
            if (rawHistory instanceof LinkedList
                    && !((LinkedList<?>) rawHistory).isEmpty()
                    && ((LinkedList<?>) rawHistory).getFirst() instanceof Entry) {
                history = (LinkedList<Entry>) rawHistory;
            } else {
                System.out.println("Attempt`s history can't be matched.");
                history = new LinkedList<>();
            }
        } else {
            history = new LinkedList<>();
        }
        Entry entry = new Entry();
        entry.setX(xValue);
        entry.setY(yValue);
        entry.setR(rValue);
        entry.setCurrentTime(currentTime);
        entry.setExecuteTime(executionTime);
        entry.setResult(isHit);
        history.add(entry);

        context.setAttribute("history", history);
        response.setHeader("Cache-Control", "no-cache");
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().println(entry.dataToJSON());


    }

    private boolean validateX(String xString) {
        try {
            double xValue = Double.parseDouble(xString);
            return xValue >= -5 && xValue <= 3;
        } catch (NumberFormatException exception) {
            return false;
        }
    }

    private boolean validateY(String yString) {
        try {
            double yValue = Double.parseDouble(yString);
            return yValue >= -3 && yValue <= 3;
        } catch (NumberFormatException exception) {
            return false;
        }
    }

    private boolean validateR(String rString) {
        try {
            double rValue = Double.parseDouble(rString);
            return rValue >= 1 && rValue <= 3;
        } catch (NumberFormatException exception) {
            return false;
        }
    }

    private boolean validateValues(String xString, String yString, String rString) {
        return validateX(xString) && validateY(yString) && validateR(rString);
    }

    private boolean checkTriangle(double xValue, double yValue, double rValue) {
        return xValue >= 0 && yValue >= 0 && yValue <= -xValue + rValue;
    }

    private boolean checkRectangle(double xValue, double yValue, double rValue) {
        return xValue <= 0 && yValue >= 0 &&
                xValue >= -rValue && yValue <= rValue / 2;
    }

    private boolean checkCircle(double xValue, double yValue, double rValue) {
        return xValue <= 0 && yValue <= 0 &&
                Math.sqrt(xValue * xValue + yValue * yValue) <= rValue;
    }

    private boolean checkHit(double xValue, double yValue, double rValue) {
        return checkTriangle(xValue, yValue, rValue) || checkRectangle(xValue, yValue, rValue) ||
                checkCircle(xValue, yValue, rValue);
    }
}
